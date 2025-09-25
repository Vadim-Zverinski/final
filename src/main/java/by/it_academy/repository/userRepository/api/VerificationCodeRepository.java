package by.it_academy.repository.userRepository.api;

import by.it_academy.repository.userRepository.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerificationCodeRepository extends JpaRepository<CodeEntity, UUID> {
}
