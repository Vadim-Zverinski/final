package by.it_academy.dto.accountDto;


import by.it_academy.dto.enums.AccountType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private String title;
    private String description;
    private BigDecimal balance;
    private AccountType type;
    private String currency;
}
