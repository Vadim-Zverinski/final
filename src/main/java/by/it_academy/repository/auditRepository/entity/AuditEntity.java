package by.it_academy.repository.auditRepository.entity;


import by.it_academy.dto.enums.Type;
import by.it_academy.dto.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "audit", schema = "finance_app")
@AllArgsConstructor
@NoArgsConstructor
public class AuditEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "dt_create")
    private Long dtCreate;

    @Column(name = "user_uuid")
    private UUID userUuid;

    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "user_fio")
    private String userFio;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    // Audit details
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Type type;

    @Column(name = "entity_id", nullable = false, length = 255)
    private String entityId;
}