package by.it_academy.DTO;

import by.it_academy.DTO.enums.UserRole;
import by.it_academy.DTO.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class User {
    UUID uuid;
    long dt_create;
    long dt_update;
    String mail;
    String fio;
    UserRole role;
    UserStatus status;
}
