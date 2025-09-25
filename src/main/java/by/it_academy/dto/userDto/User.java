package by.it_academy.dto.userDto;

import by.it_academy.dto.userDto.enums.UserRole;
import by.it_academy.dto.userDto.enums.UserStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    private UUID uuid;
    private long dtCreate;
    private long dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
}
