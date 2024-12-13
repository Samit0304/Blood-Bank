package ui;

import dao.DonorDAO;
import model.Donor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DonorManagementFrame extends JPanel {
    private JTextField nameField, bloodTypeField, ageField, genderField, contactField, addressField, donationDateField, idField;
    private JButton addButton, viewButton, editButton, deleteButton;
    private JTable donorTable; // JTable for displaying donors
    private DefaultTableModel tableModel; // Table model to manage table data

    public DonorManagementFrame() {
        setLayout(new BorderLayout()); // Set layout manager to BorderLayout

        // Create custom panel with background image
        BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setLayout(new BorderLayout());

        // Create form panel with GridLayout
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Make form panel transparent
        formPanel.setLayout(new GridLayout(9, 2, 10, 10)); // 9 rows, 2 columns, with vertical gap of 10 pixels

        // Create form fields
        nameField = new JTextField(20);
        bloodTypeField = new JTextField(3);
        ageField = new JTextField(3);
        genderField = new JTextField(10);
        contactField = new JTextField(10);
        addressField = new JTextField(20);
        donationDateField = new JTextField(10);
        idField = new JTextField(15);

        // Add components to the form panel
        formPanel.add(new JLabel("NAME:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("BLOOD TYPE:"));
        formPanel.add(bloodTypeField);
        formPanel.add(new JLabel("AGE:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("GENDER:"));
        formPanel.add(genderField);
        formPanel.add(new JLabel("CONTACT:"));
        formPanel.add(contactField);
        formPanel.add(new JLabel("ADDRESS:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("LAST DONATION DATE (YYYY-MM-DD):"));
        formPanel.add(donationDateField);
        formPanel.add(new JLabel("ID (for Edit/Delete):"));
        formPanel.add(idField);

        // Create a button panel to hold action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make button panel transparent
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center align with some space
        addButton = new JButton("ADD DONOR");
        viewButton = new JButton("VIEW DONORS");
        editButton = new JButton("EDIT DONOR");
        deleteButton = new JButton("DELETE DONOR");
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Create the top panel to hold form and buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Transparent panel
        topPanel.add(formPanel, BorderLayout.CENTER); // Form on top
        topPanel.add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom of top panel

        // Set up the JTable to display donor information
        String[] columnNames = {"ID", "Name", "Blood Type", "Age", "Gender", "Contact", "Address", "Last Donation Date"};
        tableModel = new DefaultTableModel(columnNames, 0); // Empty table model
        donorTable = new JTable(tableModel); // Create JTable with model

        // Make the table transparent
        donorTable.setOpaque(false); // Make the table itself transparent
        donorTable.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        donorTable.setForeground(Color.WHITE); // Set text color to white for readability
        donorTable.setShowGrid(false); // Hide grid lines
        donorTable.setFillsViewportHeight(true); // Ensure table fills the viewport

        // Create scroll pane for the table and make it transparent
        JScrollPane scrollPane = new JScrollPane(donorTable);
        scrollPane.setOpaque(false); // Make the scroll pane itself transparent
        scrollPane.getViewport().setOpaque(false); // Make the viewport (table container) transparent
        scrollPane.setPreferredSize(new Dimension(700, 250)); // Set preferred size for the table area

        // Use JSplitPane to split between form/buttons and table
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, scrollPane);
        splitPane.setResizeWeight(0.5); // Give equal space to both form and table
        splitPane.setOpaque(false); // Make split pane transparent
        splitPane.setDividerSize(5); // Adjust the size of the divider

        // Add split pane to the background panel
        bgPanel.add(splitPane, BorderLayout.CENTER);

        // Add the background panel to the main panel
        add(bgPanel);

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Donor donor = new Donor();
                    donor.setName(nameField.getText());
                    donor.setBloodType(bloodTypeField.getText());
                    donor.setAge(Integer.parseInt(ageField.getText()));
                    donor.setGender(genderField.getText());
                    donor.setContactNumber(contactField.getText());
                    donor.setAddress(addressField.getText());
                    donor.setLastDonationDate(java.sql.Date.valueOf(donationDateField.getText()));

                    DonorDAO donorDAO = new DonorDAO();
                    donorDAO.addDonor(donor);
                    JOptionPane.showMessageDialog(null, "Donor added successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding donor: " + e1.getMessage());
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDonors(); // Load donors and display in JTable
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Donor donor = new Donor();
                    donor.setId(id);
                    donor.setName(nameField.getText());
                    donor.setBloodType(bloodTypeField.getText());
                    donor.setAge(Integer.parseInt(ageField.getText()));
                    donor.setGender(genderField.getText());
                    donor.setContactNumber(contactField.getText());
                    donor.setAddress(addressField.getText());
                    donor.setLastDonationDate(java.sql.Date.valueOf(donationDateField.getText()));

                    DonorDAO donorDAO = new DonorDAO();
                    donorDAO.updateDonor(donor);
                    JOptionPane.showMessageDialog(null, "Donor updated successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating donor: " + e1.getMessage());
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    DonorDAO donorDAO = new DonorDAO();
                    donorDAO.deleteDonor(id);
                    JOptionPane.showMessageDialog(null, "Donor deleted successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting donor: " + e1.getMessage());
                }
            }
        });
    }

    // Method to load and display donors in the JTable
    private void viewDonors() {
        try {
            DonorDAO donorDAO = new DonorDAO();
            List<Donor> donors = donorDAO.getAllDonors();
            tableModel.setRowCount(0); // Clear the table before adding new data

            // Add each donor to the table
            for (Donor donor : donors) {
                Object[] row = {
                        donor.getId(),
                        donor.getName(),
                        donor.getBloodType(),
                        donor.getAge(),
                        donor.getGender(),
                        donor.getContactNumber(),
                        donor.getAddress(),
                        donor.getLastDonationDate()
                };
                tableModel.addRow(row); // Add donor row to table
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving donors: " + e1.getMessage());
        }
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
                backgroundImage = ImageIO.read(new File("C:\\Users\\Saurabh\\eclipse-workspace\\bbmanagementsystem\\images\\donor.jpeg")); // Set the correct image path
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
        JFrame frame = new JFrame("Donor Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DonorManagementFrame().getPanel()); // Add the DonorManagementPanel
        frame.setSize(700, 700); // Set size for the JFrame
        frame.setVisible(true); // Make the frame visible
    }
}
