package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LogoutPanel extends JPanel {
    private JButton logoutButton;

    public LogoutPanel(JFrame mainFrame) {
        // Create custom panel with background image
        BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Informative message about the importance of blood donation
        String message = "<html><p style='text-align:center;'>" +
                "<b>Blood Donation Saves Lives!</b><br>" +
                "Every two seconds, someone in the world needs blood. Your donation could be the difference " +
                "between life and death for someone in need. Donating blood is a simple, selfless act that " +
                "can save up to three lives with each donation.<br><br>" +
                "Please consider making a regular donation and spread the word to encourage others!</p></html>";

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(false);  // Make sure the message label doesn't block the background

        // Add the message label to the top of the background panel
        bgPanel.add(messageLabel, BorderLayout.NORTH);

        // Create and center the logout button
        logoutButton = new JButton("Logout");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Transparent button panel to show background
        buttonPanel.add(logoutButton);
        bgPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the background panel to the main layout
        setLayout(new BorderLayout());
        add(bgPanel);

        // Action listener for the logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "You have successfully logged out. Thank you for your contribution!", "Logged Out", JOptionPane.INFORMATION_MESSAGE);
                    // Close the main frame (simulating a logout)
                    mainFrame.dispose(); 
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Logout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(new LogoutPanel(frame));
        frame.setVisible(true);
    }

    // Custom JPanel to handle background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image
            try {
                backgroundImage = ImageIO.read(new File("C:\\Users\\Saurabh\\git\\repository\\bbmanagementsystem\\images\\logout.jpeg")); // Set the correct image path
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
