package by.it_academy.repository.classifierRepository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "category", schema = "fin_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationCategoryEntity {

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "dt_create")
    private Long dtCreate;

    @Column(name = "dt_update")
    private Long dtUpdate;

    @Column(name = "title")
    private String title;
}