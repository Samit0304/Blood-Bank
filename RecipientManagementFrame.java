package ui;

import dao.RecipientDAO;
import model.Recipient;

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

public class RecipientManagementFrame extends JPanel {
    private JTextField nameField, bloodTypeField, ageField, genderField, contactField, addressField, requestDateField, idField;
    private JButton addButton, viewButton, editButton, deleteButton;
    private JTable recipientTable; // JTable for displaying recipients
    private DefaultTableModel tableModel; // Table model to manage table data

    public RecipientManagementFrame() {
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
        requestDateField = new JTextField(10);
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
        formPanel.add(new JLabel("REQUEST DATE (YYYY-MM-DD):"));
        formPanel.add(requestDateField);
        formPanel.add(new JLabel("ID (for Edit/Delete):"));
        formPanel.add(idField);

        // Add form panel to the center of the background panel
        bgPanel.add(formPanel, BorderLayout.CENTER);

        // Create a button panel to hold action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make button panel transparent
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center align with some space
        addButton = new JButton("ADD RECIPIENT");
        viewButton = new JButton("VIEW RECIPIENTS");
        editButton = new JButton("EDIT RECIPIENT");
        deleteButton = new JButton("DELETE RECIPIENT");
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add the button panel to the top of the background panel
        bgPanel.add(buttonPanel, BorderLayout.NORTH);

        // Set up the JTable to display recipient information
        String[] columnNames = {"ID", "Name", "Blood Type", "Age", "Gender", "Contact", "Address", "Request Date"};
        tableModel = new DefaultTableModel(columnNames, 0); // Empty table model
        recipientTable = new JTable(tableModel); // Create JTable with model

        // Make the table transparent
        recipientTable.setOpaque(false); // Make the table itself transparent
        recipientTable.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        recipientTable.setForeground(Color.WHITE); // Set text color to white for readability
        recipientTable.setShowGrid(false); // Hide grid lines
        recipientTable.setFillsViewportHeight(true); // Ensure table fills the viewport

        // Create scroll pane for the table and make it transparent
        JScrollPane scrollPane = new JScrollPane(recipientTable);
        scrollPane.setOpaque(false); // Make the scroll pane itself transparent
        scrollPane.getViewport().setOpaque(false); // Make the viewport (table container) transparent
        scrollPane.setPreferredSize(new Dimension(700, 250)); // Set preferred size for the table area

        // Use JSplitPane to split between form/buttons and table
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, scrollPane);
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
                    Recipient recipient = new Recipient();
                    recipient.setName(nameField.getText());
                    recipient.setBloodType(bloodTypeField.getText());
                    recipient.setAge(Integer.parseInt(ageField.getText()));
                    recipient.setGender(genderField.getText());
                    recipient.setContactNumber(contactField.getText());
                    recipient.setAddress(addressField.getText());
                    recipient.setRequestedDate(java.sql.Date.valueOf(requestDateField.getText()));

                    RecipientDAO recipientDAO = new RecipientDAO();
                    recipientDAO.addRecipient(recipient);
                    JOptionPane.showMessageDialog(null, "Recipient added successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding recipient: " + e1.getMessage());
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRecipients(); // Load recipients and display in JTable
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Recipient recipient = new Recipient();
                    recipient.setId(id);
                    recipient.setName(nameField.getText());
                    recipient.setBloodType(bloodTypeField.getText());
                    recipient.setAge(Integer.parseInt(ageField.getText()));
                    recipient.setGender(genderField.getText());
                    recipient.setContactNumber(contactField.getText());
                    recipient.setAddress(addressField.getText());
                    recipient.setRequestedDate(java.sql.Date.valueOf(requestDateField.getText()));

                    RecipientDAO recipientDAO = new RecipientDAO();
                    recipientDAO.updateRecipient(recipient);
                    JOptionPane.showMessageDialog(null, "Recipient updated successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating recipient: " + e1.getMessage());
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    RecipientDAO recipientDAO = new RecipientDAO();
                    recipientDAO.deleteRecipient(id);
                    JOptionPane.showMessageDialog(null, "Recipient deleted successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting recipient: " + e1.getMessage());
                }
            }
        });
    }

    // Method to load and display recipients in the JTable
    private void viewRecipients() {
        try {
            RecipientDAO recipientDAO = new RecipientDAO();
            List<Recipient> recipients = recipientDAO.getAllRecipients();
            tableModel.setRowCount(0); // Clear the table before adding new data

            // Add each recipient to the table
            for (Recipient recipient : recipients) {
                Object[] row = {
                        recipient.getId(),
                        recipient.getName(),
                        recipient.getBloodType(),
                        recipient.getAge(),
                        recipient.getGender(),
                        recipient.getContactNumber(),
                        recipient.getAddress(),
                        recipient.getRequestedDate()
                };
                tableModel.addRow(row); // Add recipient row to table
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving recipients: " + e1.getMessage());
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
                backgroundImage = ImageIO.read(new File("C:\\Users\\Saurabh\\git\\repository\\bbmanagementsystem\\images\\recipient.jpeg")); // Set the correct image path
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
        JFrame frame = new JFrame("Recipient Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RecipientManagementFrame().getPanel()); // Add the RecipientManagementPanel
        frame.setSize(700, 700); // Set size for the JFrame
        frame.setVisible(true); // Make the frame visible
    }
}
