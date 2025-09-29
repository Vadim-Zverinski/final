package by.it_academy.dto.auditDto;


import by.it_academy.dto.enums.UserRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {

    private UUID uuid;

    @Valid
    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private String mail;

    @Valid
    @NotBlank(message = "ФИО обязательно")
    @Size(max = 100, message = "ФИО не должно превышать 100 символов")
    private String fio;

    @Valid
    @NotNull(message = "Роль пользователя обязательна")
    private UserRole role;
}
