package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ContactUsFrame extends JFrame {
    private JTextField nameField, emailField;
    private JTextArea messageArea;
    private JButton submitButton;

    public ContactUsFrame() {
        setTitle("Contact Us");
        setSize(700, 700); // Set size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create custom panel with background image
        BackgroundPanel bgPanel = new BackgroundPanel();

        // Use BorderLayout for the main layout
        bgPanel.setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Create a form panel to hold the fields
        JPanel formPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for flexible layout
        formPanel.setOpaque(false); // Make the form panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left (WEST)

        // Create form fields
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        messageArea = new JTextArea(5, 40);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);

        // Create a styled submit button
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0, 123, 255));  // Blue color
        submitButton.setForeground(Color.WHITE);              // White text
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Font styling
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(120, 40)); // Larger button

        // Add fields and labels to the form panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Your Name:"), gbc);  // Label for Name

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Ensure the text field is also aligned left
        formPanel.add(nameField, gbc);        // Field for Name

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Your Email:"), gbc);  // Label for Email

        gbc.gridx = 1;
        formPanel.add(emailField, gbc);       // Field for Email

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Your Message:"), gbc);  // Label for Message

        gbc.gridx = 1;
        formPanel.add(messageScrollPane, gbc); // Scrollable Text Area for Message

        // Add the form panel to the center of the background panel
        bgPanel.add(formPanel, BorderLayout.CENTER);

        // Add submit button at the bottom of the form
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.add(submitButton);
        bgPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the background panel to the frame
        add(bgPanel);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String message = messageArea.getText();

                if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Simulate sending the message
                    JOptionPane.showMessageDialog(null, "Thank you, " + name + "! Your message has been sent.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Clear fields after submission
                    nameField.setText("");
                    emailField.setText("");
                    messageArea.setText("");
                }
            }
        });

        setVisible(true); // Make frame visible
    }

    public static void main(String[] args) {
        new ContactUsFrame();
    }

    // Custom JPanel to handle background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image
            try {
                backgroundImage = ImageIO.read(new File("C:\\Users\\Saurabh\\git\\repository\\bbmanagementsystem\\images\\contact.jpeg")); // Set the correct image path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
