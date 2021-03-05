package service.impl;

import entity.Recipient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.impl.RecipientRepoImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RecipientServiceTest {

    @Mock
    RecipientRepoImpl recipientRepo;

    @Mock
    MailServiceImpl mailService;

    @InjectMocks
    RecipientServiceImpl recipientService;

    @Test
    @DisplayName("Test doMailing should invoke overloaded method x times")
    void test_doMailing() {

        var targetRecipients = List.of(new Recipient(), new Recipient(), new Recipient());

        Mockito.when(recipientRepo.getAllRecipients()).thenReturn(targetRecipients);
        Mockito.doNothing().when(mailService).sendEmailToAll(Mockito.anyString(), Mockito.anyString(), Mockito.any());

        recipientService.doMailing("Subject", "Text");

        Mockito.verify(mailService, Mockito.times(1)).sendEmailToAll(Mockito.anyString(), Mockito.anyString(), Mockito.any());

    }

}
