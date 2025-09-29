package by.it_academy.service.userService;

import by.it_academy.dto.enums.Type;
import by.it_academy.dto.userDto.UserLogin;
import by.it_academy.dto.enums.UserStatus;
import by.it_academy.repository.userRepository.api.UserRepository;
import by.it_academy.repository.userRepository.entity.UserEntity;
import by.it_academy.service.userService.api.ICabinetService;
import by.it_academy.service.userService.api.IJwtService;
import by.it_academy.service.userService.api.IMailService;
import by.it_academy.service.userService.exeption.CabinetException;
import by.it_academy.util.aspect.AuditType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AuditType(Type.USER)
@Service
@RequiredArgsConstructor
public class CabinetService implements ICabinetService {

    private final UserRepository userRepository;
    private final IMailService mailService;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean verify(String code, String mail) {
        UserEntity entity = userRepository.findByMail(mail).orElseThrow(()
                -> new IllegalArgumentException("User not found"));

        if (!mailService.verifyCode(entity.getUuid(),code))
            return false;

        entity.setStatus(UserStatus.ACTIVE);
        userRepository.save(entity);
        return true;
    }

    @Override
    public String login(UserLogin userLogin) {
        UserEntity user = userRepository.findByMail(userLogin.getMail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

//        if (!Objects.equals(user.getPassword(), userLogin.getPassword()))
//            throw new CabinetException("Invalid data");

        if(!passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid data");
        }
        return jwtService.generateToken(user);
    }
}
