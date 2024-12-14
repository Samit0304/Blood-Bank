package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HomePageFrame extends JPanel {

    private Image backgroundImage;

    public HomePageFrame() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\Saurabh\\git\\repository\\bbmanagementsystem\\images\\homepage.jpeg")); // Set the path to your image
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Hero Section
        JLabel title = new JLabel("Welcome to the Blood Bank Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(255, 0, 0)); // Red for emphasis
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add space around the title
        add(title, BorderLayout.NORTH);

        // Content Panel for Features and How it Works
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        contentPanel.setOpaque(false); // Make the content panel transparent
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Features Section
        JPanel featuresPanel = new JPanel(new BorderLayout());
        featuresPanel.setOpaque(false); // Transparent background for features section
        JLabel featuresTitle = new JLabel("Key Features of the System", JLabel.CENTER);
        featuresTitle.setFont(new Font("Arial", Font.BOLD, 18));
        featuresTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        featuresPanel.add(featuresTitle, BorderLayout.NORTH);

        JTextArea featuresDescription = new JTextArea(
                "1. Donor Management: Manage information about blood donors including personal details and donation history.\n"
                        + "2. Recipient Management: Track recipients, their requests, and their details.\n"
                        + "3. Blood Inventory Management: Maintain blood stock levels, types, and availability.\n"
                        + "4. Admin Dashboard: Manage users and generate reports.\n"
                        + "5. Secure and Efficient System: Ensures secure handling of all data and quick processing of blood bank operations."
        );
        featuresDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        featuresDescription.setLineWrap(true);
        featuresDescription.setWrapStyleWord(true);
        featuresDescription.setEditable(false);
        featuresDescription.setOpaque(false); // Transparent text area
        featuresPanel.add(new JScrollPane(featuresDescription), BorderLayout.CENTER); // Add scroll pane for better text visibility
        contentPanel.add(featuresPanel);

        // How it Works Section
        JPanel howItWorksPanel = new JPanel(new BorderLayout());
        howItWorksPanel.setOpaque(false); // Transparent background for how it works section
        JLabel howItWorksTitle = new JLabel("How the Blood Bank System Works", JLabel.CENTER);
        howItWorksTitle.setFont(new Font("Arial", Font.BOLD, 18));
        howItWorksTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        howItWorksPanel.add(howItWorksTitle, BorderLayout.NORTH);

        JTextArea howItWorksDescription = new JTextArea(
                "Step 1: Register as a Donor or Recipient.\n"
                        + "Step 2: Donors can schedule donations, while recipients can request blood based on availability.\n"
                        + "Step 3: Blood Inventory is updated in real-time, reflecting available blood types and quantities.\n"
                        + "Step 4: Admins oversee the process, ensuring smooth operations, handling users, and generating reports.\n"
                        + "Step 5: Recipients are notified once blood matching their type is available, and the donation process is tracked securely."
        );
        howItWorksDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        howItWorksDescription.setLineWrap(true);
        howItWorksDescription.setWrapStyleWord(true);
        howItWorksDescription.setEditable(false);
        howItWorksDescription.setOpaque(false); // Transparent text area
        howItWorksPanel.add(new JScrollPane(howItWorksDescription), BorderLayout.CENTER); // Add scroll pane for better text visibility
        contentPanel.add(howItWorksPanel);

        // Add the content panel to the center of the layout
        add(contentPanel, BorderLayout.CENTER);

        // Footer Section
        JPanel footerPanel = new JPanel(new GridLayout(1, 1));
        footerPanel.setBackground(new Color(220, 220, 220)); // Light gray footer background
        JLabel footerText = new JLabel("© 2024 Blood Bank Management System. All rights reserved.", JLabel.CENTER);
        footerText.setFont(new Font("Arial", Font.PLAIN, 12));
        footerText.setForeground(new Color(100, 100, 100)); // Darker gray text
        footerPanel.add(footerText);

        add(footerPanel, BorderLayout.SOUTH);
    }

    // Custom JPanel class to paint the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Blood Bank Management System - Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.add(new HomePageFrame());
        frame.setVisible(true);
    }
}
