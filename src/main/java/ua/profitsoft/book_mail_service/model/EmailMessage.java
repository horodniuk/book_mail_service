package ua.profitsoft.book_mail_service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.time.LocalDateTime;

@Document(indexName = "emails")
@Data
@Builder
public class EmailMessage {
    @Id
    private String id;
    private String subject;
    private String content;
    private String[] recipients;
    private StatusMessage status;
    private String errorMessage;
    private int attemptCount;

    @Field(type = FieldType.Date)
    private Instant lastAttemptTime;

}
