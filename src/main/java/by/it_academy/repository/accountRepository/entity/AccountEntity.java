package by.it_academy.repository.accountRepository.entity;


import by.it_academy.dto.enums.AccountType;
import by.it_academy.repository.userRepository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "account", schema = "fin_app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    private UUID uuid;

    @Column(name = "dt_create", nullable = false)
    private long dtCreate;

    @Column(name = "dt_update", nullable = false)
    private long dtUpdate;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AccountType type;

    @Column(name = "currency_id", nullable = false, length = 3)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}