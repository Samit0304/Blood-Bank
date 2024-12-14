package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AdminDashboardFrame extends JPanel {
    private JTextField usernameField, roleField, passwordField;
    private JButton addUserButton, viewUsersButton, generateReportButton;
    private JTextArea displayArea;

    public AdminDashboardFrame() {
        // Set up the background panel with the desired image
        BackgroundPanel bgPanel = new BackgroundPanel("C:\\Users\\Saurabh\\git\\repository\\bbmanagementsystem\\images\\admin.jpeg"); // Set the correct image path
        bgPanel.setLayout(new BorderLayout()); // Use BorderLayout to organize components

        // Create form panel for user management
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setOpaque(false); // Ensure the form panel is transparent

        usernameField = new JTextField(15);
        roleField = new JTextField(15);
        passwordField = new JPasswordField(15);

        addUserButton = new JButton("Add User");
        viewUsersButton = new JButton("View Users");
        generateReportButton = new JButton("Generate Reports");

        // Add fields and buttons to the form panel
        formPanel.add(new JLabel("USERNAME:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("ROLE (ADMIN/STAFF):"));
        formPanel.add(roleField);
        formPanel.add(new JLabel("PASSWORD:"));
        formPanel.add(passwordField);
        formPanel.add(addUserButton);
        formPanel.add(viewUsersButton);

        // Add form panel to the top of the background panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setOpaque(false);
        topPanel.add(formPanel);
        bgPanel.add(topPanel, BorderLayout.NORTH);

        // Display area for viewing users and logs
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);
        displayArea.setOpaque(false); // Ensure the text area is transparent
        displayArea.setBackground(new Color(0, 0, 0, 0)); // Ensure background is transparent
        displayArea.setForeground(Color.WHITE); // Set text color to make it visible
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        scrollPane.setOpaque(false); // Transparent scroll pane
        scrollPane.getViewport().setOpaque(false); // Ensure viewport is transparent
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove borders

        // Add the scroll pane to the center of the background panel
        bgPanel.add(scrollPane, BorderLayout.CENTER);

        // Report generation button at the bottom
        JPanel reportPanel = new JPanel();
        reportPanel.setOpaque(false); // Ensure the report panel is transparent
        reportPanel.add(generateReportButton);
        bgPanel.add(reportPanel, BorderLayout.SOUTH);

        // Add the background panel to the main panel
        setLayout(new BorderLayout());
        add(bgPanel, BorderLayout.CENTER);

        // Action listeners for Add User
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User user = new User();
                    user.setUsername(usernameField.getText());
                    user.setPassword(passwordField.getText());
                    user.setRole(roleField.getText());

                    UserDAO userDAO = new UserDAO();
                    userDAO.registerUser(user);
                    JOptionPane.showMessageDialog(null, "User added successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding user: " + e1.getMessage());
                }
            }
        });

        // Action listener for View Users
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserDAO userDAO = new UserDAO();
                    List<User> users = userDAO.getAllUsers();

                    displayArea.setText(""); // Clear the display area before appending new content

                    if (users.isEmpty()) {
                        displayArea.append("No users found.\n");
                    } else {
                        for (User user : users) {
                            displayArea.append(user.toString() + "\n--------------------------------------\n");
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving users: " + e1.getMessage());
                }
            }
        });

        // Action listener for Report Generation (Simulated)
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserDAO userDAO = new UserDAO();
                    List<User> users = userDAO.getAllUsers();
                    
                    // Display report in a modal
                    showReportDialog(users);
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error generating report: " + e1.getMessage());
                }
            }
        });
    }

    // Method to show the report dialog
    private void showReportDialog(List<User> users) {
        JDialog reportDialog = new JDialog((Frame) null, "User Report", true);
        reportDialog.setSize(400, 300);
        reportDialog.setLocationRelativeTo(null);

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Arial", Font.PLAIN, 14));
        reportArea.setLineWrap(true);
        reportArea.setWrapStyleWord(true);

        // Building the report
        StringBuilder report = new StringBuilder("User Report:\n\n");
        for (User user : users) {
            report.append("ID: ").append(user.getId()).append("\n");
            report.append("Username: ").append(user.getUsername()).append("\n");
            report.append("Role: ").append(user.getRole()).append("\n");
            report.append("--------------------------------------\n");
        }
        reportArea.setText(report.toString());

        JScrollPane scrollPane = new JScrollPane(reportArea);
        reportDialog.add(scrollPane);
        reportDialog.setVisible(true); // Display the dialog
    }

    // Main method to run the AdminDashboardFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.add(new AdminDashboardFrame());
        frame.setVisible(true);
    }

    // Custom JPanel class to handle background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            // Load the background image
            try {
                backgroundImage = ImageIO.read(new File(imagePath)); // Set the correct image path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Scale the image to fit the panel
            }
        }
    }
}
