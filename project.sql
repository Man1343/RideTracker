CREATE DATABASE BikeManagementSystem;
USE BikeManagementSystem;

-- 1. Rider table
CREATE TABLE Rider(
riderId INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
phone VARCHAR(10)
);

-- 2. Bike table
CREATE TABLE Bike(
bikeId INT AUTO_INCREMENT PRIMARY KEY,
brand VARCHAR(50),
model VARCHAR(50),
engineCC INT,
riderId INT,
FOREIGN KEY(riderId) REFERENCES Rider(riderId) ON DELETE CASCADE
);

-- 3. Ride table
CREATE TABLE Ride(
rideId INT AUTO_INCREMENT PRIMARY KEY,
distance DOUBLE,
time DOUBLE,
fuelUsed DOUBLE,
rideDate DATE,
mileage DOUBLE GENERATED ALWAYS AS (distance / fuelUsed) STORED,
bikeId INT,
FOREIGN KEY(bikeId) REFERENCES Bike(bikeId) ON DELETE CASCADE
);
ALTER TABLE Ride
ADD COLUMN destination VARCHAR(100) AFTER rideId;

-- 4. ServiceRecord table
CREATE TABLE ServiceRecord(
serviceId INT AUTO_INCREMENT PRIMARY KEY,
serviceType VARCHAR(100),
serviceDate DATE,
cost DOUBLE,
bikeId INT,
FOREIGN KEY(bikeId) REFERENCES Bike(bikeId) ON DELETE CASCADE
);

-- 5. Insurance table
CREATE TABLE Insurance(
insuranceId INT AUTO_INCREMENT PRIMARY KEY,
provider VARCHAR(100),
policyNumber VARCHAR(10) UNIQUE,
startDate DATE,
endDate DATE,
premium DOUBLE,
bikeId INT,
FOREIGN KEY(bikeId) REFERENCES Bike(bikeId) ON DELETE CASCADE
);

-- 6. Add-ons table
CREATE TABLE Addons(
addonId INT AUTO_INCREMENT PRIMARY KEY,
addonName VARCHAR(100),
price DOUBLE,
purchaseDate DATE,
bikeId INT,
FOREIGN KEY(bikeId) REFERENCES Bike(bikeId) ON DELETE CASCADE
);
