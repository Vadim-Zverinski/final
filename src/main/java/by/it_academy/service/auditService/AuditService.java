package by.it_academy.service.auditService;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.auditDto.Audit;
import by.it_academy.dto.enums.Type;
import by.it_academy.repository.auditRepository.api.AuditRepository;
import by.it_academy.repository.auditRepository.entity.AuditEntity;
import by.it_academy.service.auditService.api.IAuditService;
import by.it_academy.service.auditService.mapper.AuditMapper;
import by.it_academy.util.api.IPager;
import by.it_academy.util.aspect.AuditType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@AuditType(Type.REPORT)
@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;
    private final IPager pager;

    @Override
    @Transactional
    public Audit read(UUID uuid) {
        AuditEntity entity = auditRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Audit not found"));

        return auditMapper.toAudit(entity);
    }

    @Override
    @Transactional
    public PageOf<Audit> readAll(Pageable pageable) {
        Page<AuditEntity> page = auditRepository.findAll(pageable);

        return pager.getAll(
                page.map(auditMapper::toAudit));
    }

    @Transactional
    public Audit save(AuditEntity entity) {
        return auditMapper.toAudit(auditRepository.save(entity));
    }
}
