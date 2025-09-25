package by.it_academy.service.auditService.mapper;

import by.it_academy.dto.auditDto.Audit;
import by.it_academy.repository.auditRepository.entity.AuditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring" , imports = {UUID.class, Instant.class})
public interface AuditMapper {

//    @Mapping(target = "uuid", expression =
//            "java(auditEntity.getUuid() != null ? UUID.fromString(auditEntity.getUuid()) : null)")
    Audit toAudit(AuditEntity auditEntity);
    //toDO тут чет намудил



}
