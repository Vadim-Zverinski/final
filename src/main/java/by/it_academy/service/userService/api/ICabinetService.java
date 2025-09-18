package by.it_academy.service.userService.api;

public interface ICabinetService {

    boolean verify(String code, String mail);
    String login(String mail, String password);

}
