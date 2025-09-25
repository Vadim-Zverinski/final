package by.it_academy.dto.auditDto;


import by.it_academy.dto.enums.UserRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    private String uuid;
    private String mail;
    private String fio;
    private UserRole role;
}
