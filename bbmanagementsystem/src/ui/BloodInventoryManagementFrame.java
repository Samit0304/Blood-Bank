package ui;

import dao.BloodInventoryDAO;
import model.BloodInventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BloodInventoryManagementFrame extends JPanel {
    private JTextField bloodTypeField, quantityField;
    private JButton addButton, viewButton;
    private JTextArea displayArea;

    public BloodInventoryManagementFrame() {
        setLayout(new BorderLayout()); // Set layout for the main panel

        // Create custom panel with background image
        BackgroundPanel bgPanel = new BackgroundPanel();

        // Set layout manager for bgPanel
        bgPanel.setLayout(new BorderLayout());

        // Form panel using GridLayout for organized input fields and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns, with vertical and horizontal gap of 10 pixels
        formPanel.setOpaque(false); // Make the form panel transparent

        // Create text fields
        bloodTypeField = new JTextField(15);
        quantityField = new JTextField(15);

        // Add components to form panel
        formPanel.add(new JLabel("BLOOD TYPE :"));
        formPanel.add(bloodTypeField);
        formPanel.add(new JLabel("QUANTITY :"));
        formPanel.add(quantityField);

        // Create buttons
        addButton = new JButton("ADD INVENTORY");
        viewButton = new JButton("VIEW INVENTORY");

        // Add buttons to form panel
        formPanel.add(addButton);
        formPanel.add(viewButton);

        // Add form panel to the center of the background panel
        bgPanel.add(formPanel, BorderLayout.CENTER);

        // Create display area for showing inventory details
        displayArea = new JTextArea(10, 30); // Set height to 10 rows, width to 30 columns
        displayArea.setOpaque(false); // Make the text area transparent
        displayArea.setBackground(new Color(0, 0, 0, 0)); // Ensure full transparency
        displayArea.setForeground(Color.WHITE); // Set text color for visibility
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setOpaque(false); // Transparent scroll pane
        scrollPane.getViewport().setOpaque(false); // Transparent viewport
        scrollPane.setPreferredSize(new Dimension(500, 200)); // Set preferred size for the scroll pane

        // Add the scroll pane to the bottom (south) of the background panel
        bgPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add the background panel to the main panel
        add(bgPanel);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String bloodType = bloodTypeField.getText();
                    int quantity = Integer.parseInt(quantityField.getText());
                    BloodInventory bloodInventory = new BloodInventory();
                    bloodInventory.setBloodType(bloodType);
                    bloodInventory.setQuantity(quantity);

                    BloodInventoryDAO bloodInventoryDAO = new BloodInventoryDAO();
                    bloodInventoryDAO.addBloodInventory(bloodInventory);
                    JOptionPane.showMessageDialog(null, "Blood inventory added successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding blood inventory: " + e1.getMessage());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BloodInventoryDAO bloodInventoryDAO = new BloodInventoryDAO();
                    displayArea.setText(""); // Clear display area
                    for (BloodInventory inventory : bloodInventoryDAO.getAllBloodInventory()) {
                        displayArea.append(inventory.toString() + "\n");
                        if (inventory.getQuantity() < 10) { // Threshold for low inventory
                            JOptionPane.showMessageDialog(null, "Low inventory alert: " + inventory.getBloodType());
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving blood inventory: " + e1.getMessage());
                }
            }
        });
    }

    // Method to return the panel for easy addition to the main dashboard
    public JPanel getPanel() {
        return this; // Since this is a JPanel itself
    }

    // Custom JPanel to handle background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image
            try {
                backgroundImage = ImageIO.read(new File("C:\\Users\\Saurabh\\eclipse-workspace\\bbmanagementsystem\\images\\blood.jpeg")); // Set the correct image path
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

    public static void main(String[] args) {
        // This is now treated as a JPanel, so we add it to a JFrame to display it
        JFrame frame = new JFrame("Blood Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BloodInventoryManagementFrame().getPanel()); // Add the BloodInventoryManagementPanel
        frame.setSize(500, 500); // Set size for the JFrame
        frame.setVisible(true); // Make the frame visible
    }
}
