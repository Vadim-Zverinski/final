package by.it_academy.repository.classifierRepository.api;

import by.it_academy.repository.classifierRepository.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, UUID>{
}
