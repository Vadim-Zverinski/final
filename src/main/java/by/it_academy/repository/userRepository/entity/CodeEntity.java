package by.it_academy.repository.userRepository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "code", schema = "fin_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "code")
    private String code;
}
