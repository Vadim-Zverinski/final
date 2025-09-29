package by.it_academy.service.userService.api;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.userDto.User;
import by.it_academy.dto.userDto.UserCreate;
import by.it_academy.dto.userDto.UserRegistration;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

    public interface IUserService {
    void create(UserCreate userCreate);
    void create(UserRegistration userRegistration);
    void update( UUID uuid, long dtUpdate, UserCreate userCreate);
    User read(UUID uuid);
    PageOf<User> readAll(Pageable pageable);
    User readByMail(String mail);

}
