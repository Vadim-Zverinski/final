package by.it_academy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLogin {
    private String mail;
    private String password;
}
