package ui;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    public MainDashboard() {
        setTitle("Blood Bank Management System - Dashboard");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use JTabbedPane in MainDashboard
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(245, 245, 245));  // Light gray background for tabs
        tabbedPane.setForeground(new Color(60, 60, 60));     // Dark gray text for tabs

        // Add the different tabs with different frames
        tabbedPane.addTab("HOME", new HomePageFrame());
        tabbedPane.addTab("ADMINS", new AdminDashboardFrame());
        tabbedPane.addTab("DONORS", new DonorManagementFrame().getPanel());  // Extract the panel from the JFrame
        tabbedPane.addTab("RECIPIENTS", new RecipientManagementFrame().getPanel());
        tabbedPane.addTab("INVENTORY", new BloodInventoryManagementFrame().getPanel());
        tabbedPane.addTab("CONTACT US", new ContactUsFrame().getContentPane());
        tabbedPane.addTab("LOGOUT", new LogoutPanel(this));

        // Customize the tab colors
        tabbedPane.setOpaque(true);

       

        add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainDashboard();
    }
}
