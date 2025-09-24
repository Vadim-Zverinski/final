package by.it_academy.controller;

import by.it_academy.dto.User;
import by.it_academy.dto.UserLogin;
import by.it_academy.dto.UserRegistration;
import by.it_academy.service.userService.api.ICabinetService;
import by.it_academy.service.userService.api.IJwtService;
import by.it_academy.service.userService.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cabinet")
@AllArgsConstructor
public class CabinetController {

    public final ICabinetService cabinetService;
    public final IUserService userService;
    public final IJwtService jwtService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserRegistration userRegistration) {
        userService.create(userRegistration);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verification(@RequestParam String code,
                                               @RequestParam String mail) {
        if (!cabinetService.verify(code, mail)) {
            throw new RuntimeException("Invalid data");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        String token = cabinetService.login(userLogin);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

    @GetMapping(path = "/me")
    public ResponseEntity<User> about() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UUID uuid = UUID.fromString(auth.getPrincipal().toString());
        User user = userService.read(uuid);
        return ResponseEntity.ok(user);
    }

}
