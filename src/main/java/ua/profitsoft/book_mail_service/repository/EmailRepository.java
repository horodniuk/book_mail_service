package ua.profitsoft.book_mail_service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ua.profitsoft.book_mail_service.model.EmailMessage;
import ua.profitsoft.book_mail_service.model.StatusMessage;

import java.util.List;


public interface EmailRepository extends ElasticsearchRepository<EmailMessage, String> {
    List<EmailMessage> findByStatus(StatusMessage status);
}
