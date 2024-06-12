package ua.profitsoft.book_mail_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ua.profitsoft.book_mail_service.model.EmailMessage;
import ua.profitsoft.book_mail_service.model.StatusMessage;
import ua.profitsoft.book_mail_service.repository.EmailRepository;

import java.time.Instant;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailRepository emailRepository;

    public void sendEmail(EmailMessage emailMessage) {
        emailMessage.setAttemptCount(emailMessage.getAttemptCount() + 1);
        emailMessage.setLastAttemptTime(Instant.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailMessage.getRecipients());
            message.setSubject(emailMessage.getSubject());
            message.setText(emailMessage.getContent());
            mailSender.send(message);
            System.out.println("StatusMessage.SENT");
            emailMessage.setErrorMessage(null);
            emailMessage.setStatus(StatusMessage.SENT);
        } catch (Exception e) {
            emailMessage.setStatus(StatusMessage.ERROR);
            emailMessage.setErrorMessage(e.getClass().getName() + ": " + e.getMessage());
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            emailRepository.save(emailMessage);
        }
    }
}
