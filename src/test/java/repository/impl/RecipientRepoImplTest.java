package repository.impl;

import entity.Recipient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipientRepoImplTest {

    @Mock
    Connection connectionMock;

    @Mock
    Statement statementMock;

    @Mock
    ResultSet resultSetMock;

    @InjectMocks
    RecipientRepoImpl recipientRepo;

    @BeforeEach
    void initializeMocks() throws SQLException {
        recipientRepo.setConnection(connectionMock);

        when(connectionMock.createStatement()).thenReturn(statementMock);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);
    }

    @Test
    @DisplayName("Test getAllRecipients should return correct Recipient")
    void test_getAllRecipients_shouldReturnCorrectRecipient() throws SQLException {

        when(resultSetMock.next()).thenReturn(true, false);

        when(resultSetMock.getString("NAME")).thenReturn("Name");
        when(resultSetMock.getString("EMAIL")).thenReturn("name@example.com");

        Recipient expected = new Recipient("Name", "name@example.com");

        Recipient actual = recipientRepo.getAllRecipients().get(0);

        assertEquals(expected, actual);
    }

}
