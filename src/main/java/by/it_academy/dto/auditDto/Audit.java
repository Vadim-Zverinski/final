package by.it_academy.dto.auditDto;

import by.it_academy.dto.enums.Type;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Audit {

    private UUID uuid;
    private long dtCreate;

    @NotNull(message = "Пользователь обязателен")
    @Valid
    private User user;

    @NotBlank(message = "Текст обязателен")
    @Size(max = 500, message = "Текст не должен превышать 500 символов")
    private String text;

    @NotNull(message = "Тип обязателен")
    private Type type;

    @NotBlank(message = "ID обязателен")
    private String id;

}
