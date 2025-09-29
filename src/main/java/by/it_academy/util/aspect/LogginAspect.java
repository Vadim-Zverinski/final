package by.it_academy.util.aspect;

import by.it_academy.dto.auditDto.User;
import by.it_academy.dto.enums.Type;
import by.it_academy.dto.enums.UserRole;
import by.it_academy.repository.auditRepository.api.AuditRepository;
import by.it_academy.repository.auditRepository.entity.AuditEntity;
import by.it_academy.service.auditService.api.IAuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogginAspect {

    private final AuditRepository auditRepository;

    @AfterReturning("within(@by.it_academy.util.aspect.AuditType *)")
    public void logAudit(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();

        // значения аннотации
        AuditType auditType = targetClass.getAnnotation(AuditType.class);
        Type type = auditType.value();

        // текущий пользователь тут
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //  данныеиз auth
        UUID userUuid = UUID.randomUUID();
        String mail = auth != null ? auth.getName() : "anonymous";
        String fio = "Unknown";
        String role = "UNKNOWN";

        AuditEntity entity = AuditEntity.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(Instant.now().toEpochMilli())
                .userUuid(userUuid)
                .userMail(mail)
                .userFio(fio)
                .userRole(null)
                .text("Вызван метод: " + joinPoint.getSignature().getName())
                .type(type)
                .entityId(targetClass.getSimpleName())
                .build();

        auditRepository.save(entity);

        log.info("AUDIT LOG: {} - {} - {}", type, entity.getText(), mail);
    }
}

