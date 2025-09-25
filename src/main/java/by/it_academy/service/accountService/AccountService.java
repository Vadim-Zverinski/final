package by.it_academy.service.accountService;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.accountDto.Account;
import by.it_academy.repository.accountRepository.api.AccountRepository;
import by.it_academy.repository.accountRepository.entity.AccountEntity;
import by.it_academy.service.accountService.api.IAccountService;
import by.it_academy.service.accountService.mapper.AccountMapper;
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
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final IPager pager;

    @Transactional
    @Override
    public void create(Account account) {
        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        AccountEntity entity = accountMapper.toEntity(account, uuid, time);
        accountRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(UUID uuid, long dtUpdate, Account account) {
        AccountEntity entity = accountRepository.findById(uuid).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if (entity.getDtUpdate() != dtUpdate) {
            throw new OptimisticLockException("User was updated");
        }

        entity.setDtUpdate(account.getDtUpdate());
        entity.setTitle(account.getTitle());
        entity.setDescription(account.getDescription());
        entity.setBalance(account.getBalance());
        entity.setType(account.getType());
        entity.setCurrency(account.getCurrency());
        accountRepository.save(entity);
    }

    @Override
    public Account read(UUID uuid) {
        AccountEntity entity = accountRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return accountMapper.toDto(entity);
    }

    @Override
    public PageOf<Account> readAll(Pageable pageable) {
        Page<AccountEntity> page = accountRepository.findAll(pageable);

        return pager.getAll(
                page.map(accountMapper::toDto));
    }
}
