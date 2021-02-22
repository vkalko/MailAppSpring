package service;

public interface RecipientService {

    void addRecipient(String name, String email);

    void doMailing(String subject, String text);

    void doMailing(String subject, String text, String email);

}
