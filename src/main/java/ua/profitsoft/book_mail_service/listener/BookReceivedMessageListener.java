package ua.profitsoft.book_mail_service.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ua.profitsoft.book_mail_service.factory.EmailMessageFactory;
import ua.profitsoft.book_mail_service.model.BookReceivedMessage;
import ua.profitsoft.book_mail_service.model.EmailMessage;
import ua.profitsoft.book_mail_service.service.EmailService;

@Component
@RequiredArgsConstructor
public class BookReceivedMessageListener {
    private final EmailService emailService;
    private final EmailMessageFactory emailMessageFactory;

    @KafkaListener(topics = "${kafka.topic.bookReceived}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload BookReceivedMessage message) {
        System.out.println("Received message: " + message);
        EmailMessage emailMessage = emailMessageFactory.createEmailMessage(message);
        emailService.sendEmail(emailMessage);

    }
}
