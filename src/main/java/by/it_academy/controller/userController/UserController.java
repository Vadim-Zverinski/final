package by.it_academy.controller.userController;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.userDto.User;
import by.it_academy.dto.userDto.UserCreate;
import by.it_academy.service.userService.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cabinet")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    public final IUserService userService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserCreate userCreate) {
        userService.create(userCreate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageOf<User>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(userService.readAll(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.read(uuid));
    }

    @PutMapping("{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> update(@PathVariable("uuid") UUID uuid,
                                         @PathVariable("dt_update") long dtUpdate,
                                         @RequestBody UserCreate userCreate) {
        userService.update(uuid, dtUpdate, userCreate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
