CREATE DATABASE blood_bank;
USE blood_bank;

-- Table for users (authentication)

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'staff') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from users;
INSERT INTO users (username, password, role) VALUES
('admin', 'admin', 'admin'),
('staff1', 'staff1', 'staff'),
('staff2', 'staff2', 'staff');




insert into users values(1,'saurabh','saurabh123');
-- Table for donors
CREATE TABLE donors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    blood_type VARCHAR(3),
    age INT,
    gender VARCHAR(10),
    contact_number VARCHAR(15),
    address VARCHAR(255),
    last_donation_date DATEdonors
);
select * from donors;



-- Table for recipients
CREATE TABLE recipients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    blood_type VARCHAR(3),
    age INT,
    gender VARCHAR(10),
    contact_number VARCHAR(15),
    address VARCHAR(255),
    requested_date DATE
);

-- Table for blood inventory
CREATE TABLE blood_inventory (
    blood_type VARCHAR(3) PRIMARY KEY,
    quantity INT
);
