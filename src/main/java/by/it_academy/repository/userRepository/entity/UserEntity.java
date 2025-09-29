package by.it_academy.repository.userRepository.entity;


import by.it_academy.dto.enums.UserRole;
import by.it_academy.dto.enums.UserStatus;
import by.it_academy.repository.accountRepository.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Table(name = "user", schema = "fin_app",uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private UUID uuid;

    @Column(name = "dt_create", nullable = false)
    private long dtCreate;

    @Column(name = "dt_update", nullable = false)
    private long dtUpdate;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String fio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts = new ArrayList<>();
}