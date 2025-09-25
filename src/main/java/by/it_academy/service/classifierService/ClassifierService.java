package by.it_academy.service.classifierService;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.classifierDto.Currency;
import by.it_academy.dto.classifierDto.OperationCategory;
import by.it_academy.repository.classifierRepository.api.CurrencyRepository;
import by.it_academy.repository.classifierRepository.api.OperationCategoryRepository;
import by.it_academy.repository.classifierRepository.entity.CurrencyEntity;
import by.it_academy.repository.classifierRepository.entity.OperationCategoryEntity;
import by.it_academy.service.classifierService.api.IClassifierService;
import by.it_academy.service.classifierService.mapper.ClassifierMapper;
import by.it_academy.util.api.IPager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClassifierService implements IClassifierService {

    private final IPager pager;
    private final CurrencyRepository currencyRepository;
    private final OperationCategoryRepository operationCategoryRepository;
    private final ClassifierMapper classifierMapper;

    @Override
    @Transactional
    public void createCurrency(Currency currency) {
        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        CurrencyEntity entity = classifierMapper.currencyToEntity(currency, uuid, time);
        currencyRepository.save(entity);
    }

    @Override
    @Transactional
    public void createOperationCategory(OperationCategory operationCategory) {
        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        OperationCategoryEntity entity = classifierMapper
                .operationCategoryToEntity(operationCategory, uuid, time);
        operationCategoryRepository.save(entity);

    }

    @Override
    @Transactional
    public PageOf<Currency> readAllCurrency(Pageable pageable) {
        Page<CurrencyEntity> page = currencyRepository.findAll(pageable);

        return pager.getAll(
                page.map(classifierMapper::toCurrency));
    }

    @Override
    @Transactional
    public PageOf<OperationCategory> readAllOperationCategory(Pageable pageable) {
        Page<OperationCategoryEntity> page = operationCategoryRepository.findAll(pageable);

        return pager.getAll(
                page.map(classifierMapper::toOperationCategory));
    }
}
