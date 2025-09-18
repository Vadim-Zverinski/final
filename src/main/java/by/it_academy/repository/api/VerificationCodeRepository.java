package by.it_academy.repository.api;

import by.it_academy.repository.entity.CodeEntity;
import by.it_academy.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerificationCodeRepository extends JpaRepository<CodeEntity, UUID> {
}
