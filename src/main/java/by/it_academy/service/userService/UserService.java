package by.it_academy.service.userService;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.enums.Type;
import by.it_academy.dto.userDto.UserCreate;
import by.it_academy.dto.userDto.User;
import by.it_academy.dto.userDto.UserRegistration;
import by.it_academy.repository.userRepository.api.UserRepository;
import by.it_academy.repository.userRepository.api.VerificationCodeRepository;
import by.it_academy.repository.userRepository.entity.CodeEntity;
import by.it_academy.repository.userRepository.entity.UserEntity;
import by.it_academy.service.userService.api.IMailService;
import by.it_academy.service.userService.api.IUserService;
import by.it_academy.service.userService.mapper.UserMapper;
import by.it_academy.util.api.IPager;
import by.it_academy.util.api.IVerifyCodeGeneration;
import by.it_academy.util.aspect.AuditType;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@AuditType(Type.USER)
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository codeRepository;
    private final IVerifyCodeGeneration authCode;
    private final IPager pager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final IMailService mailService;

    @Transactional
    @Override
    public void create(UserCreate userCreate) {
        boolean exists = userRepository.existsByMail(userCreate.getMail());
        if (exists) {
            throw new IllegalArgumentException("User already exists");
        }

        System.out.println("User pass: " + userCreate.getPassword());

        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        UserEntity entity = userMapper.userCreateToEntity(userCreate, uuid, time);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        //userRepository.save(entity);

        try {
            userRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User already exists");
        }

        String code = authCode.generateCode();

        codeRepository.save(
                CodeEntity.builder()
                        .uuid(uuid)
                        .code(code)
                        .build()

        );

        mailService.mail(userCreate.getMail(),code);
    }

    @Override
    public void create(UserRegistration userRegistration) {
        boolean exists = userRepository.existsByMail(userRegistration.getMail());
        if (exists) {
            throw new IllegalArgumentException("User already exists");
        }

        UserCreate user = userMapper.registrationToCreate(userRegistration);
        create(user);
        //create(userMapper.registrationToCreate(userRegistration));
    }


    @Override
    public void update(UUID uuid, long dtUpdate, UserCreate userCreate) {
        UserEntity entity = userRepository.findById(uuid).orElseThrow(()
                -> new IllegalArgumentException("User not found"));

        if (entity.getDtUpdate() != dtUpdate) {
            throw new IllegalArgumentException("User was updated");
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
        UserEntity entity = userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toUserDto(entity);
    }

    @Override
    public PageOf<User> readAll(Pageable pageable) {
        Page<UserEntity> page = userRepository.findAll(pageable);

        return pager.getAll(
                page.map(userMapper::toUserDto));

    }

    public User readByMail(String mail) {
        UserEntity entity = userRepository.findByMail(mail)
                .orElseThrow(()  -> new IllegalArgumentException("User not found"));
        return userMapper.userToDto(entity);
    }

}
