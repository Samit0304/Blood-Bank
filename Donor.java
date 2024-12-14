package model;

//src/main/java/model/Donor.java


import java.sql.Date;

public class Donor {
 private int id;
 private String name;
 private String bloodType;
 private int age;
 private String gender;
 private String contactNumber;
 private String address;
 private Date lastDonationDate;

 // Getters and setters

 public int getId() {
     return id;
 }

 public void setId(int id) {
     this.id = id;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getBloodType() {
     return bloodType;
 }

 public void setBloodType(String bloodType) {
     this.bloodType = bloodType;
 }

 public int getAge() {
     return age;
 }

 public void setAge(int age) {
     this.age = age;
 }

 public String getGender() {
     return gender;
 }

 public void setGender(String gender) {
     this.gender = gender;
 }

 public String getContactNumber() {
     return contactNumber;
 }

 public void setContactNumber(String contactNumber) {
     this.contactNumber = contactNumber;
 }

 public String getAddress() {
     return address;
 }

 public void setAddress(String address) {
     this.address = address;
 }

 public Date getLastDonationDate() {
     return lastDonationDate;
 }

 public void setLastDonationDate(Date lastDonationDate) {
     this.lastDonationDate = lastDonationDate;
 }

 @Override
 public String toString() {
     return "Donor{" +
             "id=" + id +
             ", name='" + name + '\'' +
             ", bloodType='" + bloodType + '\'' +
             ", age=" + age +
             ", gender='" + gender + '\'' +
             ", contactNumber='" + contactNumber + '\'' +
             ", address='" + address + '\'' +
             ", lastDonationDate=" + lastDonationDate +
             '}';
 }
}

