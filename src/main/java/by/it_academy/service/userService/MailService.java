package by.it_academy.service.userService;

import by.it_academy.repository.api.VerificationCodeRepository;
import by.it_academy.repository.entity.CodeEntity;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final VerificationCodeRepository codeRepository;

    public void mail(String mail,String code) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mail);
            helper.setSubject("Verification");
            helper.setText("Your verification code is: " + code);
            mailSender.send(message);
            //mailStorage.incrementVerifiedMailCount(userId); // null
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyCode(UUID uuid, String code){
        CodeEntity entity = codeRepository.findById(uuid).orElseThrow();
       return Objects.equals(entity.getCode(),code);
    }
}
