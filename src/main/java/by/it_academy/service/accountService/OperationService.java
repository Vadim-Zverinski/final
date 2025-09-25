package by.it_academy.service.accountService;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.accountDto.Account;
import by.it_academy.dto.accountDto.Operation;
import by.it_academy.dto.userDto.User;
import by.it_academy.repository.accountRepository.api.AccountRepository;
import by.it_academy.repository.accountRepository.api.OperationRepository;
import by.it_academy.repository.accountRepository.entity.AccountEntity;
import by.it_academy.repository.accountRepository.entity.OperationEntity;
import by.it_academy.repository.userRepository.entity.UserEntity;
import by.it_academy.service.accountService.api.IOperationService;
import by.it_academy.service.accountService.mapper.OperationMapper;
import by.it_academy.util.api.IPager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService implements IOperationService {

    private  final OperationRepository operationRepository;
    private  final AccountRepository accountRepository;
    private  final OperationMapper operationMapper;
    private  final IPager pager;

    @Transactional
    @Override
    public void create(Operation operation) {
        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        OperationEntity entity = operationMapper.toEntity(operation, uuid, time);
        operationRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(UUID uuid, long dtUpdate, Operation operation) {
        OperationEntity entity = operationRepository.findById(uuid).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if (entity.getDtUpdate() != dtUpdate) {
            throw new OptimisticLockException("User was updated");
        }

        entity.setDtUpdate(operation.getDtUpdate());
        entity.setDate(operation.getDate());
        entity.setDescription(operation.getDescription());
        entity.setCategory(operation.getCategory());
        entity.setValue(operation.getValue());
        entity.setCurrency(operation.getCurrency());
        operationRepository.save(entity);
    }

    @Override
    public PageOf<Operation> readAll(Pageable pageable) {
        Page<OperationEntity> page = operationRepository.findAll(pageable);

        return pager.getAll(
                page.map(operationMapper::toDto));
    }

    @Override
    public void delete(UUID accountUuid, UUID operationUuid, long dtUpdate) {
        AccountEntity entity  = accountRepository.findById(accountUuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        entity.setDtUpdate(dtUpdate);
        accountRepository.save(entity);
        operationRepository.deleteById(operationUuid);
    }


}
