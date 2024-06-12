package ua.profitsoft.book_mail_service.factory;

import org.springframework.stereotype.Component;
import ua.profitsoft.book_mail_service.model.BookReceivedMessage;
import ua.profitsoft.book_mail_service.model.EmailMessage;
import ua.profitsoft.book_mail_service.model.StatusMessage;
import ua.profitsoft.book_mail_service.template.EmailTemplate;


@Component
public class EmailMessageFactory {
    public EmailMessage createEmailMessage(BookReceivedMessage bookMessage) {
        String subject = EmailTemplate.createSubject(bookMessage);
        String content = EmailTemplate.createContent(bookMessage);
        String[] recipients = EmailTemplate.getRecipients();

        return EmailMessage.builder()
                .subject(subject)
                .content(content)
                .recipients(recipients)
                .status(StatusMessage.SENT)
                .build();
    }
}
