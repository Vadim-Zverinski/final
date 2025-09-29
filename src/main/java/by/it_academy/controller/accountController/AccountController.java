package by.it_academy.controller.accountController;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.accountDto.Account;
import by.it_academy.service.accountService.api.IAccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final IAccountService accountService;



    @PostMapping
    public ResponseEntity<String> create(@RequestBody Account account) {
        accountService.create(account);
             return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @GetMapping
    public ResponseEntity<PageOf<Account>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.readAll(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Account> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.read(uuid));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> update(@PathVariable("uuid") UUID uuid,
                                         @PathVariable("dt_update") long dtUpdate,
                                         @RequestBody Account account) {
        accountService.update(uuid, dtUpdate, account);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
