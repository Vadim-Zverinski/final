package by.it_academy.service.userService;

import by.it_academy.dto.PageOf;
import by.it_academy.dto.userDto.UserCreate;
import by.it_academy.dto.userDto.User;
import by.it_academy.dto.userDto.UserRegistration;
import by.it_academy.repository.userRepository.api.UserRepository;
import by.it_academy.repository.userRepository.api.VerificationCodeRepository;
import by.it_academy.repository.userRepository.entity.CodeEntity;
import by.it_academy.repository.userRepository.entity.UserEntity;
import by.it_academy.service.userService.api.IUserService;
import by.it_academy.service.userService.mapper.UserMapper;
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
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository codeRepository;
    private final IVerifyCodeGeneration authCode;
    private final IPager pager;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public void create(UserCreate userCreate) {
        UserEntity userEntity = userRepository.findByMail( userCreate.getMail()).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if(Objects.equals(userCreate.getFio(),userEntity.getFio()) ||
                Objects.equals(userCreate.getPassword(),userEntity.getPassword()))
        {
            throw new OptimisticLockException("User already exist");
        }

        UUID uuid = UUID.randomUUID();
        long time = Instant.now().toEpochMilli();

        UserEntity entity = userMapper.userCreateToEntity(userCreate, uuid, time);
        userRepository.save(entity);

        codeRepository.save(
                CodeEntity.builder()
                        .uuid(uuid)
                        .code(authCode.generateCode())
                        .build()
        );
    }

    @Override
    public void create(UserRegistration userRegistration) {
        UserEntity userEntity = userRepository.findByMail( userRegistration.getMail()).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if(Objects.equals(userRegistration.getFio(),userEntity.getFio()) ||
                Objects.equals(userRegistration.getPassword(),userEntity.getPassword()))
        {
            throw new OptimisticLockException("User already exist");
        }

        UserCreate user = userMapper.registrationToCreate(userRegistration);
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
//    public User read(UUID uuid) {
//        Optional<UserEntity> user = userRepository.findById(uuid);
//
//        return User.builder()
//                .uuid(uuid)
//                .dtCreate(user.get().getDtCreate())
//                .dtUpdate(user.get().getDtUpdate())
//                .fio(user.get().getFio())
//                .mail(user.get().getMail())
//                .role(user.get().getRole())
//                .status(user.get().getStatus())
//                .build();
//    }
    public User read(UUID uuid) {
        UserEntity entity = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toUserDto(entity);
    }

    @Override
    public PageOf<User> readAll(Pageable pageable) {
        Page<UserEntity> page = userRepository.findAll(pageable);

        return pager.getAll(
                page.map(userMapper::toUserDto));
    }
}
