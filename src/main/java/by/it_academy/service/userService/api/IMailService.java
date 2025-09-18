package by.it_academy.service.userService.api;

import java.util.UUID;

public interface IMailService {

    void mail(String mail,String code);
    boolean verifyCode(UUID uuid, String code);
}
