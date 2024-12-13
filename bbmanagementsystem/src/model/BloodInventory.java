package model;

//src/main/java/model/BloodInventory.java


public class BloodInventory {
 private String bloodType; // e.g., "A+", "O-", etc.
 private int quantity;     // Number of blood units available

 // Getters and setters

 public String getBloodType() {
     return bloodType;
 }

 public void setBloodType(String bloodType) {
     this.bloodType = bloodType;
 }

 public int getQuantity() {
     return quantity;
 }

 public void setQuantity(int quantity) {
     this.quantity = quantity;
 }

 @Override
 public String toString() {
     return "BloodInventory{" +
             "bloodType='" + bloodType + '\'' +
             ", quantity=" + quantity +
             '}';
 }
}

