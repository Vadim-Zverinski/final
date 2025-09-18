package by.it_academy.controller;

import by.it_academy.DTO.UserLogin;
import by.it_academy.DTO.UserRegistration;
import by.it_academy.service.userService.api.ICabinetService;
import by.it_academy.service.userService.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cabinet")
@AllArgsConstructor
public class CabinetController {

    public final ICabinetService cabinetService;
    public final IUserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserRegistration userRegistration) {
        userService.create(userRegistration);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verification(@RequestParam String code, @RequestParam String mail) {
        cabinetService.verify(code, mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
        cabinetService.login(userLogin.getMail(), userLogin.getPassword());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
