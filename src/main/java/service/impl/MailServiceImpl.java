package service.impl;

import entity.Recipient;
import service.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    private final Properties properties;
    private String userName;
    private String password;

    public MailServiceImpl(String host, int port, String userName, String password) {
        properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void sendEmail(String subject, String text, Recipient recipient) {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
