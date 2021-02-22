package service;

import entity.Recipient;

import java.util.List;

public interface MailService {

    void sendEmail(String subject, String text, Recipient recipient);

    void sendEmailToAll(String subject, String text, List<Recipient> recipients);

}
