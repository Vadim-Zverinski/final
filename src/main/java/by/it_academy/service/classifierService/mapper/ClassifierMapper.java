package by.it_academy.service.classifierService.mapper;

import by.it_academy.dto.classifierDto.Currency;
import by.it_academy.dto.classifierDto.OperationCategory;
import by.it_academy.repository.classifierRepository.entity.CurrencyEntity;
import by.it_academy.repository.classifierRepository.entity.OperationCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring" , imports = {UUID.class, Instant.class})
public interface ClassifierMapper {

    @Mapping(target = "uuid", expression = "java(UUID.fromString(currency.getUuid()))")
    Currency toCurrency(CurrencyEntity currency);

    OperationCategory toOperationCategory(OperationCategoryEntity operationCategory);

    @Mapping(target = "uuid", expression = "java(uuid.toString())")
    @Mapping(target = "dtCreate", expression = "java(time)")
    @Mapping(target = "dtUpdate", expression = "java(time)")
    CurrencyEntity currencyToEntity(Currency currency, UUID uuid, long time);



    @Mapping(target = "uuid", expression = "java(uuid.toString())")
    @Mapping(target = "dtCreate", expression = "java(time)")
    @Mapping(target = "dtUpdate", expression = "java(time)")
    OperationCategoryEntity operationCategoryToEntity(
            OperationCategory operationCategory, UUID uuid, long time);

}
