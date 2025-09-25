package by.it_academy.repository.accountRepository.api;


import by.it_academy.repository.accountRepository.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<OperationEntity, UUID> {
}
