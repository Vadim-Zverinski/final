package by.it_academy.controller.auditController;


import by.it_academy.dto.PageOf;
import by.it_academy.dto.auditDto.Audit;
import by.it_academy.dto.classifierDto.Currency;
import by.it_academy.dto.userDto.User;
import by.it_academy.service.auditService.api.IAuditService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AuditController {

    private final IAuditService auditService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Audit> get(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(auditService.read(uuid));
    }

    @GetMapping
    public ResponseEntity<PageOf<Audit>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(auditService.readAll(pageable));
    }
}
