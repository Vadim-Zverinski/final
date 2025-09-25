package by.it_academy.service.auditService.api;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.auditDto.Audit;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAuditService {

    Audit read(UUID uuid);
    PageOf<Audit> readAll(Pageable pageable);
}
