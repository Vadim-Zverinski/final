package by.it_academy.service.accountService.mapper;

import by.it_academy.dto.accountDto.Account;
import by.it_academy.dto.enums.UserRole;
import by.it_academy.dto.enums.UserStatus;
import by.it_academy.repository.accountRepository.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, Instant.class})
public interface AccountMapper {

    @Mapping(target = "uuid", expression = "java(uuid.toString())")
    @Mapping(target = "dtCreate", expression = "java(time)")
    @Mapping(target = "dtUpdate", expression = "java(time)")
    AccountEntity toEntity(Account account, UUID uuid, long time);

    Account toDto(AccountEntity accountEntity);

}
