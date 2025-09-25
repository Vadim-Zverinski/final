package by.it_academy.service.accountService.api;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.accountDto.Operation;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IOperationService {

    void create(Operation operation);
    void update(UUID uuid, long dtUpdate, Operation operation);
    PageOf<Operation> readAll(Pageable pageable);
    void delete(UUID accountUuid, UUID operationUuid, long dtUpdate);
}
