package by.it_academy.dto.userDto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {

    @Valid
    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private   String mail;

    @Valid
    @NotBlank(message = "ФИО обязательно")
    @Size(max = 100, message = "ФИО не должно превышать 100 символов")
    private   String fio;

    @Valid
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен быть не короче 6 символов")
    private   String password;


}
