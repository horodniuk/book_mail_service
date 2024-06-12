package ua.profitsoft.book_mail_service.template;

import ua.profitsoft.book_mail_service.model.BookReceivedMessage;

public class EmailTemplate {
    public static final String ADMIN_EMAIL = "max.horod55@gmail.com";

    public static String createSubject(BookReceivedMessage message) {
        return "New Book Received: " + message.getName();
    }

    public static String createContent(BookReceivedMessage message) {
        return "Book Details:\n" +
               "Name: " + message.getName() + "\n" +
               "Genre: " + message.getGenre() + "\n" +
               "Year Published: " + message.getYearPublished() + "\n" +
               "Author: " + message.getAuthorFirstName() + " " + message.getAuthorLastName();
    }

    public static String[] getRecipients() {
        return new String[]{ADMIN_EMAIL};
    }
}
