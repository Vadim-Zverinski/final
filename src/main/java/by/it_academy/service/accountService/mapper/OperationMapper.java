package by.it_academy.service.accountService.mapper;


import by.it_academy.dto.accountDto.Operation;
import by.it_academy.repository.accountRepository.entity.OperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Instant.class})
public interface OperationMapper {

    @Mapping(target = "uuid", expression = "java(uuid.toString())")
    @Mapping(target = "dtCreate", expression = "java(time)")
    @Mapping(target = "dtUpdate", expression = "java(time)")
    OperationEntity toEntity(Operation operation, UUID uuid, long time);

    Operation toDto(OperationEntity operationEntity);

}
