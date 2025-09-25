package by.it_academy.dto.accountDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private long date;
    private String description;
    private String category;
    private BigDecimal value;
    private String currency;
}
