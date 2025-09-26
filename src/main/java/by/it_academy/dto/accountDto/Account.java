package by.it_academy.dto.accountDto;


import by.it_academy.dto.enums.AccountType;
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
public class Account {

    private UUID uuid;
    private long dtCreate;
    private long dtUpdate;

    @NotBlank(message = "Название счёта обязательно")
    @Size(max = 100, message = "Название счёта не должно превышать 100 символов")
    private String title;

    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    private String description;

    @NotNull(message = "Баланс обязателен")
    @DecimalMin(value = "0.0", inclusive = true, message = "Баланс не может быть отрицательным")
    private BigDecimal balance;

    @NotNull(message = "Тип счёта обязателен")
    private AccountType type;

    @NotBlank(message = "Валюта обязательна")
    @Size(min = 3, max = 3, message = "Код валюты должен содержать 3 символа")
    private String currency;
}
