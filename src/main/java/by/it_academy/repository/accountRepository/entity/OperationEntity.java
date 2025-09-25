package by.it_academy.repository.accountRepository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "operation", schema = "fin_app")
@Getter
@Setter
@NoArgsConstructor
public class OperationEntity {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "dt_create")
    private Long dtCreate;

    @Column(name = "dt_update")
    private Long dtUpdate;

    @Column(name = "date")
    private Long date;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "currency_id")
    private String currency;

    @Column(name = "account_id")
    private String account;
}
