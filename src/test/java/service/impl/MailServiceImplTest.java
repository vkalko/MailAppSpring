package service.impl;

import entity.Recipient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MailServiceImplTest {

    @Spy
    MailServiceImpl mailService;

    @Test
    void verify_sendEmailMethodInvocation() {
        List<Recipient> recipients = List.of(new Recipient(), new Recipient(), new Recipient());

        Mockito.doNothing().when(mailService).sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.any(Recipient.class));

        mailService.sendEmailToAll("Subject", "Text", recipients);

        Mockito.verify(mailService, Mockito.times(recipients.size()))
                .sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.any(Recipient.class));
    }

}
