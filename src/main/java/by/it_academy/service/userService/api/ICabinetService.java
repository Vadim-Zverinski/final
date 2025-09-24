package by.it_academy.service.userService.api;

import by.it_academy.dto.UserLogin;

public interface ICabinetService {

    boolean verify(String code, String mail);
    String login(UserLogin userLogin);

}
