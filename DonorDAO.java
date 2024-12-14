package dao;
//src/main/java/dao/DonorDAO.java

import model.Donor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorDAO {
    private Connection connection;

    public DonorDAO() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "Saura@2003");
    }

    // Add Donor with Date Validation
    public void addDonor(Donor donor) {
        String query = "INSERT INTO donors (name, blood_type, age, gender, contact_number, address, last_donation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, donor.getName());
            preparedStatement.setString(2, donor.getBloodType());
            preparedStatement.setInt(3, donor.getAge());
            preparedStatement.setString(4, donor.getGender());
            preparedStatement.setString(5, donor.getContactNumber());
            preparedStatement.setString(6, donor.getAddress());

            // Date validation before setting to SQL
            String dateString = donor.getLastDonationDate().toString(); // Get the date as a string
            if (!isValidDate(dateString)) {
                throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD.");
            }

            preparedStatement.setDate(7, donor.getLastDonationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to validate the date format (YYYY-MM-DD)
    private boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}"); // Matches format YYYY-MM-DD
    }

    // Retrieve all donors
    public List<Donor> getAllDonors() {
        List<Donor> donors = new ArrayList<>();
        String query = "SELECT * FROM donors";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Donor donor = new Donor();
                donor.setId(resultSet.getInt("id"));
                donor.setName(resultSet.getString("name"));
                donor.setBloodType(resultSet.getString("blood_type"));
                donor.setAge(resultSet.getInt("age"));
                donor.setGender(resultSet.getString("gender"));
                donor.setContactNumber(resultSet.getString("contact_number"));
                donor.setAddress(resultSet.getString("address"));
                donor.setLastDonationDate(resultSet.getDate("last_donation_date"));
                donors.add(donor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donors;
    }

    // Method to search donors by name or blood type
    /*public List<Donor> searchDonors(String searchTerm) {
        List<Donor> donors = new ArrayList<>();
        String query = "SELECT * FROM donors WHERE name LIKE ? OR blood_type LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setString(2, "%" + searchTerm + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Donor donor = new Donor();
                donor.setId(resultSet.getInt("id"));
                donor.setName(resultSet.getString("name"));
                donor.setBloodType(resultSet.getString("blood_type"));
                donor.setAge(resultSet.getInt("age"));
                donor.setGender(resultSet.getString("gender"));
                donor.setContactNumber(resultSet.getString("contact_number"));
                donor.setAddress(resultSet.getString("address"));
                donor.setLastDonationDate(resultSet.getDate("last_donation_date"));
                donors.add(donor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donors;
    }*/

    // Method to update donor details with date validation
    public void updateDonor(Donor donor) {
        String query = "UPDATE donors SET name = ?, blood_type = ?, age = ?, gender = ?, contact_number = ?, address = ?, last_donation_date = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, donor.getName());
            preparedStatement.setString(2, donor.getBloodType());
            preparedStatement.setInt(3, donor.getAge());
            preparedStatement.setString(4, donor.getGender());
            preparedStatement.setString(5, donor.getContactNumber());
            preparedStatement.setString(6, donor.getAddress());

            // Validate date format before setting to SQL
            String dateString = donor.getLastDonationDate().toString();
            if (!isValidDate(dateString)) {
                throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD.");
            }

            preparedStatement.setDate(7, donor.getLastDonationDate());
            preparedStatement.setInt(8, donor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete a donor by ID
    public void deleteDonor(int id) {
        String query = "DELETE FROM donors WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
