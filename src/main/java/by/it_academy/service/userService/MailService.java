package by.it_academy.service.userService;

import by.it_academy.dto.enums.Type;
import by.it_academy.repository.userRepository.api.VerificationCodeRepository;
import by.it_academy.repository.userRepository.entity.CodeEntity;
import by.it_academy.service.userService.api.IMailService;
import by.it_academy.util.aspect.AuditType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@AuditType(Type.USER)
@Service
@AllArgsConstructor
public class MailService implements IMailService {

    private final JavaMailSender mailSender;
    private final VerificationCodeRepository codeRepository;

    public void mail(String mail,String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("faleoltest@gmail.com");

            helper.setTo(mail);
            helper.setSubject("Verification");
            helper.setText("Your verification code is: " + code);
            System.out.println("код: " + code);
            System.out.println("Маил: " + mail);
            mailSender.send(message);
            //mailStorage.incrementVerifiedMailCount(userId); // null
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyCode(UUID uuid, String code){
        CodeEntity entity = codeRepository.findById(uuid).orElseThrow(()
                -> new IllegalArgumentException("User not found"));
       return Objects.equals(entity.getCode(),code);
    }
}
