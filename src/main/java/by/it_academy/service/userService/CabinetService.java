package by.it_academy.service.userService;

import by.it_academy.DTO.enums.UserStatus;
import by.it_academy.repository.api.UserRepository;
import by.it_academy.repository.entity.UserEntity;
import by.it_academy.service.userService.api.ICabinetService;
import by.it_academy.service.userService.api.IMailService;
import by.it_academy.service.userService.exeption.CabinetException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CabinetService implements ICabinetService {

    private final UserRepository userRepository;
    private final IMailService mailService;

    @Override
    public boolean verify(String code, String mail) {
        UserEntity entity = userRepository.findByMail(mail).orElseThrow();
        if (!mailService.verifyCode(entity.getUuid(),code))
            return false;
        entity.setStatus(UserStatus.ACTIVE);
        userRepository.save(entity);
        return true;
    }

    @Override
    public String login(String mail, String password) {
        UserEntity user = userRepository.findByMail(mail)
                .orElseThrow(() -> new CabinetException("User not found"));
        if (Objects.equals(user.getPassword(), password))
            throw new RuntimeException("Invalid data");
        return "Login is success";
    }
}
