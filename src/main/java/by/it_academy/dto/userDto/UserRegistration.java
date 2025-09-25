package by.it_academy.dto.userDto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {

    private   String mail;
    private   String fio;
    private   String password;


}
