package repository.impl;

import entity.Recipient;
import org.springframework.beans.factory.annotation.Value;
import repository.RecipientRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipientRepoImpl implements RecipientRepo {

    @Value("${database.url}")
    private String dbURL;

    @Value("${database.user}")
    private String userName;

    @Value("${database.password}")
    private String password;

    private Connection connection;

    public RecipientRepoImpl() throws SQLException {
        //connection = DriverManager.getConnection(dbURL, userName, password);
    }

    //for tests
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Recipient> getAllRecipients() {
        final String SELECT_ALL_QUERY = "SELECT NAME, EMAIL " +
                "FROM RECIPIENTS";
        List<Recipient> recipients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(SELECT_ALL_QUERY);
            while (results.next()) {
                recipients.add(toEntity(results));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
        return recipients;
    }

    @Override
    public void addRecipient(Recipient recipient) {
        final String ADD_RECIPIENT_QUERY = String.format("INSERT INTO RECIPIENTS(NAME, EMAIL) " +
                "VALUES('%s', '%s');", recipient.getName(), recipient.getEmail());
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(ADD_RECIPIENT_QUERY);
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public void removeRecipientByEmail(String email) {
        final String DELETE_RECIPIENT_QUERY = String.format("DELETE FROM RECIPIENTS " +
                "WHERE EMAIL='%s';", email);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DELETE_RECIPIENT_QUERY);
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public Recipient getRecipientByEmail(String email) {
        final String SELECT_RECIPIENT_QUERY = String.format("SELECT NAME, EMAIL " +
                "FROM RECIPIENTS " +
                "WHERE EMAIL='%s';", email);
        Recipient recipient = new Recipient();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(SELECT_RECIPIENT_QUERY);
            while (results.next()) {
                recipient = toEntity(results);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
        return recipient;
    }

    private Recipient toEntity(ResultSet resultSet) throws SQLException {
        Recipient recipient = new Recipient();
        recipient.setName(resultSet.getString("NAME"));
        recipient.setEmail(resultSet.getString("EMAIL"));

        return recipient;
    }
}
