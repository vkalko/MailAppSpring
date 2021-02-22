package repository;

import entity.Recipient;

import java.util.List;

public interface RecipientRepo {

    List<Recipient> getAllRecipients();

    void addRecipient(Recipient recipient);

    void removeRecipientByEmail(String email);

    Recipient getRecipientByEmail(String email);

}
