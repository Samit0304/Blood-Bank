package dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    // Constructor to establish connection with the database
    public UserDAO() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank", "root", "Saura@2003");
    }

    // Method to validate user login based on username and password
    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // If user exists, return true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if validation fails
    }

    // Method to register a new user in the database
    public void registerUser(User user) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(); // Initialize the list to avoid null
        String query = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Iterate over the result set and add users to the list
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;  // Return the list of users (empty list if no users found)
    }

    // Example: Method to register a new user and print confirmation
    public void exampleRegisterUser() {
        User newUser = new User();
        newUser.setUsername("john_doe");
        newUser.setPassword("securepassword");
        newUser.setRole("admin");

        registerUser(newUser);
        System.out.println("User registered: " + newUser.getUsername());
    }

    // Example: Method to validate a user and print whether the user is valid
    public void exampleValidateUser() {
        boolean isValid = validateUser("john_doe", "securepassword");

        if (isValid) {
            System.out.println("User is valid.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // Example: Method to retrieve and print all users in the database
    public void exampleGetAllUsers() {
        List<User> users = getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);  // This will call User's toString method
            }
        }
    }

    // Main method to run the examples
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();

            // Example to register a user
            userDAO.exampleRegisterUser();

            // Example to validate a user
            userDAO.exampleValidateUser();

            // Example to retrieve and display all users
            userDAO.exampleGetAllUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
