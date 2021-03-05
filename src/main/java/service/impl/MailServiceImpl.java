package service.impl;

import config.MailConfig;
import entity.Recipient;
import service.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Arrays;
import java.util.List;

public class MailServiceImpl implements MailService {

    private MailConfig mailConfig;

    public MailServiceImpl() {}

    public MailServiceImpl(MailConfig config) {
        mailConfig = config;
    }

    @Override
    public void sendEmail(String subject, String text, Recipient recipient) {
        Session session = Session.getInstance(mailConfig.getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.getUserName(), mailConfig.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setSubject(subject);
            message.setFrom(new InternetAddress("mycompany@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipient.getEmail()));

            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(text, "text/plain; charset=utf-8");
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.printf("Message was sent to %s\n", Arrays.toString(message.getAllRecipients()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailToAll(String subject, String text, List<Recipient> recipients) {
        for (Recipient recipient: recipients) {
            sendEmail(subject, text, recipient);
        }
    }

}
