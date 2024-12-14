package ui;

// src/main/java/ui/LoginFrame.java

import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Image backgroundImage;

    public LoginFrame() {
        // Set title and default settings
        setTitle("Blood Bank Management System - Login");
        setSize(400, 500); // Increase size to fit the logo and text fields
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            // Load the background image
            backgroundImage = ImageIO.read(new File("C:\\Users\\Saurabh\\git\\repository\\bbmanagementsystem\\images\\logo.jpeg")); // Ensure the path is correct
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the custom panel with the background image
        JPanel panel = new BackgroundPanel();
        add(panel);

        placeComponents(panel);

        setVisible(true);
    }

    // Method to place components in the panel
    private void placeComponents(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical box layout

        // Add image logo to the top
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the image
        try {
            // Set the logo as an image icon for the label
            ImageIcon logoIcon = new ImageIcon(ImageIO.read(new File("C:\\Users\\Saurabh\\eclipse-workspace\\bbmanagementsystem\\images\\logo.jpeg")));
            Image logoImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Resize the image
            imageLabel.setIcon(new ImageIcon(logoImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space above the image
        panel.add(imageLabel);

        // Create a transparent panel to hold the fields and buttons
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Make panel transparent
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username Label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        formPanel.add(userLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between components

        // Username TextField
        usernameField = new JTextField(20);
        usernameField.setMaximumSize(new Dimension(200, 30)); // Set maximum size for text field
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between components

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(passwordLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between components

        // Password Field
        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(200, 30)); // Set maximum size for password field
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space between components

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(loginButton);

        // Add formPanel to the main panel
        panel.add(Box.createVerticalGlue()); // Push the form to the center vertically
        panel.add(formPanel);
        panel.add(Box.createVerticalGlue()); // Push the form to the center vertically

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                UserDAO userDAO = null;
                try {
                    userDAO = new UserDAO();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (userDAO != null && userDAO.validateUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    new MainDashboard(); // Navigate to main dashboard
                    dispose(); // Close the login window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                }
            }
        });
    }

    // Custom JPanel to draw background image
    class BackgroundPanel extends JPanel {
        //@Override
       /* protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }*/
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
