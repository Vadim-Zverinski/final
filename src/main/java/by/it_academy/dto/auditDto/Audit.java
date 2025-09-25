package by.it_academy.dto.auditDto;

import by.it_academy.dto.enums.Type;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Audit {
    private UUID uuid;
    private long dtCreate;
    private User user;
    private String text;
    private Type type;
    private String id;

}
