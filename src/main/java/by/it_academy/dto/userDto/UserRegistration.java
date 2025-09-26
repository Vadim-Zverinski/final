package by.it_academy.dto.userDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private   String mail;

    @NotBlank(message = "ФИО обязательно")
    @Size(max = 100, message = "ФИО не должно превышать 100 символов")
    private   String fio;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен быть не короче 6 символов")
    private   String password;


}
