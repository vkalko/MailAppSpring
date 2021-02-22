package service.impl;

import entity.Recipient;
import repository.RecipientRepo;
import service.MailService;
import service.RecipientService;

import java.util.List;

public class RecipientServiceImpl implements RecipientService {

    private RecipientRepo recipientRepo;
    private MailService mailService;

    public RecipientServiceImpl(RecipientRepo recipientRepo, MailService mailService) {
        this.recipientRepo = recipientRepo;
        this.mailService = mailService;
    }

    @Override
    public void addRecipient(String name, String email) {
        recipientRepo.addRecipient(new Recipient(name, email));
    }

    @Override
    public void doMailing(String subject, String text) {
        List<Recipient> recipientList = recipientRepo.getAllRecipients();
        mailService.sendEmailToAll(subject, text, recipientList);
    }

    @Override
    public void doMailing(String subject, String text, String email) {
        mailService.sendEmail(subject,
                text,
                recipientRepo.getRecipientByEmail(email));
    }
}
