package by.it_academy.dto.classifierDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private String title;
    private String description;
}
