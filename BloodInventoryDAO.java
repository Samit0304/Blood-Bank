package dao;

//src/main/java/dao/BloodInventoryDAO.java


import model.BloodInventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloodInventoryDAO {
 private Connection connection;

 public BloodInventoryDAO() throws SQLException {
     try {
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","Saura@2003");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};
 }

 // Method to get all blood inventory records
 public List<BloodInventory> getAllBloodInventory() {
     List<BloodInventory> inventoryList = new ArrayList<>();
     String query = "SELECT * FROM blood_inventory";

     try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query)) {
         while (resultSet.next()) {
             BloodInventory bloodInventory = new BloodInventory();
             bloodInventory.setBloodType(resultSet.getString("blood_type"));
             bloodInventory.setQuantity(resultSet.getInt("quantity"));
             inventoryList.add(bloodInventory);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }

     return inventoryList;
 }

 // Method to update the quantity of a specific blood type
 public void updateBloodQuantity(String bloodType, int quantity) {
     String query = "UPDATE blood_inventory SET quantity = ? WHERE blood_type = ?";

     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
         preparedStatement.setInt(1, quantity);
         preparedStatement.setString(2, bloodType);
         int rowsUpdated = preparedStatement.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println("Blood inventory updated successfully for type: " + bloodType);
         } else {
             System.out.println("No such blood type found: " + bloodType);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 // Method to add a new blood type to the inventory
 public void addBloodType(String bloodType, int quantity) {
     String query = "INSERT INTO blood_inventory (blood_type, quantity) VALUES (?, ?)";

     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
         preparedStatement.setString(1, bloodType);
         preparedStatement.setInt(2, quantity);
         preparedStatement.executeUpdate();
         System.out.println("Blood type " + bloodType + " added successfully with quantity: " + quantity);
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 // Method to get the quantity of a specific blood type
 public int getBloodQuantity(String bloodType) {
     String query = "SELECT quantity FROM blood_inventory WHERE blood_type = ?";
     int quantity = 0;

     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
         preparedStatement.setString(1, bloodType);
         ResultSet resultSet = preparedStatement.executeQuery();
         if (resultSet.next()) {
             quantity = resultSet.getInt("quantity");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }

     return quantity;
 }

public void addBloodInventory(BloodInventory bloodInventory) {
	// TODO Auto-generated method stub
	
}
}
