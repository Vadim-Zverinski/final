package by.it_academy.service.userService.exeption;

public class CabinetException extends RuntimeException {
    public CabinetException(String message) {
        super(message);
    }

    public CabinetException(String message, Throwable cause) {
        super(message, cause);
    }

    public CabinetException(Throwable cause) {
        super(cause);
    }
}
