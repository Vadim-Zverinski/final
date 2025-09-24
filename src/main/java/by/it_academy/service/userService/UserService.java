package by.it_academy.service.userService;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.UserCreate;
import by.it_academy.dto.User;
import by.it_academy.dto.UserRegistration;
import by.it_academy.dto.enums.UserRole;
import by.it_academy.dto.enums.UserStatus;
import by.it_academy.repository.api.UserRepository;
import by.it_academy.repository.api.VerificationCodeRepository;
import by.it_academy.repository.entity.CodeEntity;
import by.it_academy.repository.entity.UserEntity;
import by.it_academy.service.userService.api.IUserService;
import by.it_academy.service.userService.api.UserDetailsMapper;
import by.it_academy.util.api.IPager;
import by.it_academy.util.api.IVerifyCodeGeneration;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository codeRepository;
    private final IVerifyCodeGeneration authCode;
    private final IPager pager;
    private final UserDetailsMapper userDetailsMapper;

    @Transactional
    @Override
    public void create(UserCreate userCreate) {
        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        userRepository.save(
                UserEntity.builder()
                        .uuid(uuid)
                        .dtCreate(time)
                        .dtUpdate(time)
                        .fio(userCreate.getFio())
                        .mail(userCreate.getMail())
                        .role(UserRole.USER)
                        .status(UserStatus.WAITING_ACTIVATION)
                        .password(userCreate.getPassword())
                        .build()
        );

        codeRepository.save(
                CodeEntity.builder()
                        .uuid(uuid)
                        .code(authCode.generateCode())
                        .build()
        );
    }

    @Override
    public void create(UserRegistration userRegistration) {
        UserCreate user = userDetailsMapper.registrationToCreate(userRegistration);
        create(user);
        //create(userMapper.registrationToCreate(userRegistration));
    }


    @Override
    public void update(UUID uuid, long dtUpdate, UserCreate userCreate) {
        UserEntity entity = userRepository.findById(uuid).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if (entity.getDtUpdate() != dtUpdate) {
            throw new OptimisticLockException("User was updated");
        }

        entity.setDtUpdate(userCreate.getDtUpdate());
        entity.setMail(userCreate.getMail());
        entity.setFio(userCreate.getFio());
        entity.setRole(userCreate.getRole());
        entity.setStatus(userCreate.getStatus());
        entity.setPassword(userCreate.getPassword());
        userRepository.save(entity);

    }

    @Override
    public User read(UUID uuid) {
        Optional<UserEntity> user = userRepository.findById(uuid);

        return User.builder()
                .uuid(uuid)
                .dtCreate(user.get().getDtCreate())
                .dtUpdate(user.get().getDtUpdate())
                .fio(user.get().getFio())
                .mail(user.get().getMail())
                .role(user.get().getRole())
                .status(user.get().getStatus())
                .build();
    }

    @Override
    public PageOf<User> readAll(Pageable pageable) {
        Page<UserEntity> page = userRepository.findAll(pageable);

        return pager.getAll(
                page.map(entity -> User.builder()
                        .uuid(entity.getUuid())
                        .dtCreate(entity.getDtCreate())
                        .dtUpdate(entity.getDtUpdate())
                        .fio(entity.getFio())
                        .mail(entity.getMail())
                        .role(entity.getRole())
                        .status(entity.getStatus())
                        .build())
        );
    }
}
