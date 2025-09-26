package by.it_academy.dto.accountDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Operation {

    private UUID uuid;
    private long dtCreate;
    private long dtUpdate;

    @NotNull(message = "Дата транзакции обязательна")
    private Long date;

    @NotBlank(message = "Описание обязательно")
    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    private String description;

    @NotBlank(message = "Категория обязательна")
    @Size(max = 50, message = "Название категории не должно превышать 50 символов")
    private String category;

    @NotNull(message = "Сумма обязательна")
    @DecimalMin(value = "0.0", inclusive = true, message = "Сумма не может быть отрицательной")
    private BigDecimal value;

    @NotBlank(message = "Валюта обязательна")
    @Size(min = 3, max = 3, message = "Код валюты должен содержать 3 символа")
    private String currency;
}