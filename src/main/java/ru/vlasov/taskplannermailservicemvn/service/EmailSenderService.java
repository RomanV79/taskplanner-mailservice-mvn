package ru.vlasov.taskplannermailservicemvn.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.vlasov.taskplannermailservicemvn.dto.MessageDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public void sendEmail(MessageDto messageDto) {
        log.info("Start create email message for: {}", messageDto.getEmail());
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = messageDto.getBody();
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(messageDto.getEmail());
            helper.setSubject(messageDto.getTitle());
            helper.setFrom("task-planner-1@mail.ru");
            mailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            log.info("Email doesn't send. Exception -> {}", e.getMessage());
        }
    }
}
