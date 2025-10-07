# RideTracker
A menu-driven Java based program.

**Java-based Ride Tracking System (RevTrackProject)** built in **Eclipse + MySQL**
# 🏍️ Ride Tracker - Bike Management System

## 📘 Overview
**Ride Tracker (RevTrackProject)** is a Java-based console application designed to help riders efficiently manage their bikes, track rides, maintain service records, monitor insurance details and handle bike add-ons.  
The project connects to a **MySQL database** and demonstrates CRUD (Create, Read, Update, Delete) operations using **JDBC**.

---

## 🧩 Features

### 🧍 Rider Management
- Add new riders with name, email and phone number  
- View all registered riders  
- Update rider details  
- Delete rider records  

### 🏍️ Bike Management
- Add, view, update and delete bike details  
- Each bike is linked to a specific rider  

### 📍 Ride Management
- Log ride distance, time, fuel used and destination  
- Automatically calculates mileage (`distance / fuelUsed`)  
- View, update or delete ride details  

### 🔧 Service Records
- Maintain service history for each bike  
- Record service type, cost and date  

### 🧾 Insurance Management
- Add insurance provider details  
- Store policy number, start & end date and premium amount  

### 🎁 Add-ons Management
- Track extra accessories (e.g., helmets, mirrors, covers)  
- Maintain purchase date and price for each add-on  

---

## 🗄️ Database Schema (MySQL)

The project uses a database named **`BikeManagementSystem`**.  
Below are the key tables and relationships:

- `Rider` → stores rider details  
- `Bike` → linked to `Rider` via `riderId` (ON DELETE CASCADE)  
- `Ride` → linked to `Bike`  
- `ServiceRecord` → linked to `Bike`  
- `Insurance` → linked to `Bike`  
- `Addons` → linked to `Bike`  

**All dependent records are deleted automatically when a parent record is removed.**

---

## ⚙️ Technologies Used

| Component | Technology |
|------------|-------------|
| Language | Java (JDK 17 or above recommended) |
| Database | MySQL |
| Connectivity | JDBC |
| IDE | Eclipse IDE |
| Version Control | Git & GitHub |

---

## 🧱 Project Structure

rideTracker/
│
├── src/com/anudip/project/ridetrack/
│   ├── RevTrackProject.java        # Main class with menu navigation
│   ├── RiderManager.java           # Handles CRUD for Rider
│   ├── BikeManager.java            # Handles CRUD for Bike
│   ├── RideManager.java            # Handles CRUD for Ride
│   ├── ServiceManager.java         # Handles CRUD for Service Records
│   ├── InsuranceManager.java       # Handles CRUD for Insurance
│   ├── AddonManager.java           # Handles CRUD for Add-ons
│
├── database/
│   └── BikeManagementSystem.sql    # SQL schema (all CREATE TABLE statements)
│
└── README.md                       # Project documentation

---

## 💻 How to Run the Project

1. **Open Eclipse**
   - File → Import → Existing Projects into Workspace  
   - Select the `rideTracker` folder

2. **Set up MySQL Database**
   - Open MySQL and create the database:
     ```sql
     CREATE DATABASE BikeManagementSystem;
     USE BikeManagementSystem;
     ```
   - Run all the `CREATE TABLE` statements from your SQL file.

3. **Configure Database Connection**
   - Open `RevTrackProject.java`
   - Update the connection details if needed:
     ```java
     String url = "jdbc:mysql://localhost:3306/BikeManagementSystem";
     String user = "root";
     String password = "root";
     ```

4. **Run the Program**
   - Right-click `RevTrackProject.java` → Run As → Java Application
   - Use the console menu to perform various operations.

---

## 🧠 Key Learning Concepts

- Database Connectivity using **JDBC**
- Implementation of **CRUD operations**
- **Foreign key relationships** and **ON DELETE CASCADE**
- Code modularization using **Manager Classes**
- Realistic use-case modeling (Ride Tracking System)

---

## 👨‍💻 Author

**Man Pareta**  
📧 Email: manpareta13@gmail.com  
💻 Java Developer | Web Development Enthusiast  

---

## 🏁 Future Enhancements
- Add GUI (JavaFX or Swing)  
- Integrate live mileage tracking using sensors or APIs  
- Add user authentication (Login/Signup)  
- Generate ride and service reports in PDF  

---

## 📜 License
This project is open-source and available under the **MIT License**.

---


Would you like me to **tailor this README** for your **GitHub profile (with emojis, badges, and a modern look)** — like a portfolio-style project page? It would make your repository look more professional and attractive.
