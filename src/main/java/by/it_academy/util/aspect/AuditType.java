package by.it_academy.util.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import by.it_academy.dto.enums.Type;

@Target(ElementType.TYPE) // можно ставить на класс
@Retention(RetentionPolicy.RUNTIME) // аннотация доступна во время выполнения
public @interface AuditType {
    Type value();
}
