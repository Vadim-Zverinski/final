package by.it_academy.service.userService.api;

import by.it_academy.DTO.UserCreate;
import by.it_academy.DTO.UserRegistration;
import org.apache.catalina.User;

import java.util.List;

public interface IUserService {
void create(UserCreate userCreate);
void create(UserRegistration userRegistration);
void update();
User read();
List<User> readAll();

}
