package by.it_academy.repository.accountRepository.api;


import by.it_academy.repository.accountRepository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
}
