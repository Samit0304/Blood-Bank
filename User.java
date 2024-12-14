package model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    // Getters and setters for User attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Override the toString method to display user information
    @Override
    public String toString() {
        return "User ID: " + id + "\nUsername: " + username + "\nRole: " + role + "\n";
    }
}
