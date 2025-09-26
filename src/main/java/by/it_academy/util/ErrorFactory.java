package by.it_academy.util;

import by.it_academy.dto.errorDto.ErrorResponse;
import by.it_academy.dto.errorDto.StructuredErrorResponse;

import java.util.List;
public final class ErrorFactory {

    private ErrorFactory() {
        // utility class
    }

    // --- Общая ошибка (всегда список) ---
    public static List<ErrorResponse> ofError(String message) {
        return List.of(
                ErrorResponse.builder()
                        .logref("error")
                        .message(message)
                        .build()
        );
    }

    // --- Структурированная ошибка (всегда список) ---
    public static List<StructuredErrorResponse> ofStructured(List<StructuredErrorResponse.FieldError> fieldErrors) {
        return List.of(
                StructuredErrorResponse.builder()
                        .logref("structured_error")
                        .errors(fieldErrors)
                        .build()
        );
    }

    // --- Вспомогательный метод для полей ---
    public static StructuredErrorResponse.FieldError fieldError(String field, String message) {
        return StructuredErrorResponse.FieldError.builder()
                .field(field)
                .message(message)
                .build();
    }
}