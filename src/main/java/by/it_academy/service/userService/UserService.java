package by.it_academy.service.userService;

import by.it_academy.DTO.UserCreate;
import by.it_academy.DTO.UserRegistration;
import by.it_academy.DTO.enums.UserRole;
import by.it_academy.DTO.enums.UserStatus;
import by.it_academy.repository.api.UserRepository;
import by.it_academy.repository.api.VerificationCodeRepository;
import by.it_academy.repository.entity.CodeEntity;
import by.it_academy.repository.entity.UserEntity;
import by.it_academy.service.userService.api.IUserService;
import by.it_academy.util.api.IAuthCode;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository codeRepository;
    private final IAuthCode authCode;

    @Override
    public void create(UserCreate userCreate) {

    }

    @Transactional
    @Override
    public void create(UserRegistration userRegistration) {

        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        userRepository.save(
                UserEntity.builder()
                        .uuid(uuid)
                        .dtCreate(time)
                        .dtUpdate(time)
                        .fio(userRegistration.fio)
                        .mail(userRegistration.mail)
                        .role(UserRole.USER)
                        .status(UserStatus.WAITING_ACTIVATION)
                        .password(userRegistration.password)
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
    public void update() {

    }

    @Override
    public User read() {
        return null;
    }

    @Override
    public List<User> readAll() {
        return List.of();
    }
}
