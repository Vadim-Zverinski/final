package by.it_academy.controller.accountController;


import by.it_academy.dto.PageOf;
import by.it_academy.dto.accountDto.Account;
import by.it_academy.dto.accountDto.Operation;
import by.it_academy.service.accountService.api.IOperationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class OperationController {

    private final IOperationService operationService;


    @PostMapping("/{uuid}/operation")
    public ResponseEntity<String> create(@RequestBody Operation operation) {
        operationService.create(operation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{uuid}/operation")
    public ResponseEntity<PageOf<Operation>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(operationService.readAll(pageable));
    }

    @PutMapping("/{uuid}/operation/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<String> update(@PathVariable("uuid") UUID uuid,
                                         @PathVariable("dt_update") long dtUpdate,
                                         @RequestBody Operation operation) {
        operationService.update(uuid, dtUpdate, operation);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{uuid}/operation/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<String> delete(@PathVariable UUID uuid,
                                         @PathVariable(name = "uuid_operation") UUID operationUuid,
                                         @PathVariable(name = "dt_update") long dtUpdate) {
        operationService.delete(uuid, operationUuid, dtUpdate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
