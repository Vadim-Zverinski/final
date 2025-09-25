package by.it_academy.service.accountService.api;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.accountDto.Account;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAccountService {

    void create(Account account);
    void update(UUID uuid, long dtUpdate, Account account);
    Account read(UUID uuid);
    PageOf<Account> readAll(Pageable pageable);
}
