package by.it_academy.controller;

import by.it_academy.dto.errorDto.ErrorResponse;
import by.it_academy.dto.errorDto.StructuredErrorResponse;
import by.it_academy.util.ErrorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<List<ErrorResponse>> handleIllegal(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                ErrorFactory.ofError(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StructuredErrorResponse>> handleValidation(
            MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> ErrorFactory.fieldError(err.getField(),
                        err.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(
                ErrorFactory.ofStructured(errors),
                HttpStatus.BAD_REQUEST
        );
    }
}
