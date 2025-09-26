package by.it_academy.dto.userDto;

import by.it_academy.dto.enums.UserRole;
import by.it_academy.dto.enums.UserStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
public class UserCreate {

  private   UUID uuid;
  private  long dtCreate;
  private  long dtUpdate;

  @NotBlank(message = "Email обязателен")
  @Email(message = "Некорректный формат email")
  private  String mail;

  @NotBlank(message = "ФИО обязательно")
  @Size(max = 100, message = "ФИО не должно превышать 100 символов")
  private  String fio;

  @NotNull(message = "Роль пользователя обязательна")
  private  UserRole role;

  @NotNull(message = "Статус пользователя обязателен")
  private  UserStatus status;

  @NotBlank(message = "Пароль обязателен")
  @Size(min = 6, message = "Пароль должен быть не короче 6 символов")
  private  String password;
}
