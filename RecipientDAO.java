package dao;

import model.Recipient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipientDAO {
    private Connection connection;

    public RecipientDAO() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","Saura@2003");
    }

    // Method to add a recipient
    public void addRecipient(Recipient recipient) throws SQLException {
        String query = "INSERT INTO recipients (name, blood_type, age, gender, contact_number, address, requested_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, recipient.getName());
            statement.setString(2, recipient.getBloodType());
            statement.setInt(3, recipient.getAge());
            statement.setString(4, recipient.getGender());
            statement.setString(5, recipient.getContactNumber());
            statement.setString(6, recipient.getAddress());
            statement.setDate(7, recipient.getRequestedDate());
            statement.executeUpdate();
        }
    }

    // Method to retrieve all recipients
    public List<Recipient> getAllRecipients() throws SQLException {
        List<Recipient> recipients = new ArrayList<>();
        String query = "SELECT * FROM recipients";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Recipient recipient = new Recipient();
                recipient.setId(resultSet.getInt("id"));
                recipient.setName(resultSet.getString("name"));
                recipient.setBloodType(resultSet.getString("blood_type"));
                recipient.setAge(resultSet.getInt("age"));
                recipient.setGender(resultSet.getString("gender"));
                recipient.setContactNumber(resultSet.getString("contact_number"));
                recipient.setAddress(resultSet.getString("address"));
                recipient.setRequestedDate(resultSet.getDate("requested_date"));
                recipients.add(recipient);
            }
        }
        return recipients;
    }

    // Method to update recipient details
    public void updateRecipient(Recipient recipient) throws SQLException {
        String query = "UPDATE recipients SET name = ?, blood_type = ?, age = ?, gender = ?, contact_number = ?, address = ?, requested_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, recipient.getName());
            statement.setString(2, recipient.getBloodType());
            statement.setInt(3, recipient.getAge());
            statement.setString(4, recipient.getGender());
            statement.setString(5, recipient.getContactNumber());
            statement.setString(6, recipient.getAddress());
            statement.setDate(7, recipient.getRequestedDate());
            statement.setInt(8, recipient.getId());
            statement.executeUpdate();
        }
    }

    // Method to delete a recipient by ID
    public void deleteRecipient(int id) throws SQLException {
        String query = "DELETE FROM recipients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
