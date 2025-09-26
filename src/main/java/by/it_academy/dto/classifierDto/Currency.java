package by.it_academy.dto.classifierDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Currency {
    private UUID uuid;
    private long dtCreate;
    private long dtUpdate;

    @NotBlank(message = "Название валюты обязательно")
    @Size(max = 50, message = "Название валюты не должно превышать 50 символов")
    private String title;

    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    private String description;
}
