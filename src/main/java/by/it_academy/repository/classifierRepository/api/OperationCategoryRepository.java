package by.it_academy.repository.classifierRepository.api;

import by.it_academy.repository.classifierRepository.entity.OperationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationCategoryRepository extends JpaRepository<OperationCategoryEntity, UUID>{
}
