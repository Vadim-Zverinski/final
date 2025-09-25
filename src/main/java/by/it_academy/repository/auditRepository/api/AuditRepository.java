package by.it_academy.repository.auditRepository.api;

import by.it_academy.repository.auditRepository.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditRepository extends JpaRepository<AuditEntity, UUID> {
}

