package by.it_academy.dto.errorDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StructuredErrorResponse {
    private String logref;
    private List<FieldError> errors;

    @Data
    @Builder
    public static class FieldError {
        private String field;
        private String message;
    }
}
