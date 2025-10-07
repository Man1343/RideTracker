package ridetracker;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

// Manages CRUD operation for Ride table
public class RideManager {

	// Method for managing Ride operations
    public static void manageRide(Scanner sc, Connection conn) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Manage Rides ---");
            System.out.println("1. Add New Ride");
            System.out.println("2. View All Rides");
            System.out.println("3. Update Ride");
            System.out.println("4. Delete Ride");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: 
                	addRide(sc, conn); // Create new Ride
                	break;
                
                case 2: 
                	viewRides(conn); // Read all Rides
                	break;
                	
                case 3: 
                	updateRide(sc, conn); // Update Ride
                	break;
                	
                case 4: 
                	deleteRide(sc, conn); // Delete Ride
                	break;
                	
                case 5: 
                	System.out.println("Returning to Main Menu..."); 
                	break;
                	
                default: 
                	System.out.println("Invalid choice, try again.");
            }
        }
    }

    // Add new Ride to record
    private static void addRide(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Ride Date (YYYY-MM-DD): ");
            String dateStr = sc.nextLine();
            Date rideDate = Date.valueOf(dateStr);
            
            System.out.print("Enter Destination: ");
            String destination = sc.nextLine();

            System.out.print("Enter Distance (in km): ");
            float distance = Float.parseFloat(sc.nextLine());

            System.out.print("Enter Duration (in hours): ");
            int duration = Integer.parseInt(sc.nextLine());
            
            System.out.print("Enter fuel (in liter) used during entire ride: ");
            int fuelUsed = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "INSERT INTO Ride(destination, distance, time, fuelUsed, rideDate, bikeId) VALUES (?, ?, ?, ?, ?, ?)";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, destination);
                pstmt.setFloat(2, distance);
                pstmt.setInt(3, duration);
                pstmt.setInt(4, fuelUsed);
                pstmt.setDate(5, rideDate);
                pstmt.setInt(6, bikeId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " ride added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding ride: " + e.getMessage());
        }
    }

    // View all Rides
    private static void viewRides(Connection conn) {
        try {
            String sql = "SELECT * FROM Ride";
            try (var stmt = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {
                System.out.println("\nAll Rides:");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("rideId") + " | " +
                        rs.getString("destination") + " | " +
                        rs.getFloat("distance") + " kms | " +
                        rs.getInt("time") + " hours | " +
                        rs.getDouble("fuelUsed") + " liters | " +
                        rs.getDate("rideDate") + " | " +
                        rs.getDouble("mileage") + " km/l | Bike ID: " +
                        rs.getInt("bikeId")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving rides: " + e.getMessage());
        }
    }

    // Update Ride by rideId
    private static void updateRide(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Ride ID to update: ");
            int rideId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new Ride Date (YYYY-MM-DD): ");
            String dateStr = sc.nextLine();
            Date rideDate = Date.valueOf(dateStr);
            
            System.out.print("Enter new Destination: ");
            String destination = sc.nextLine();

            System.out.print("Enter new Distance (in km): ");
            float distance = Float.parseFloat(sc.nextLine());

            System.out.print("Enter new Duration (in hours): ");
            int duration = Integer.parseInt(sc.nextLine());
            
            System.out.print("Enter new fuel (in liter) used during entire ride: ");
            int fuelUsed = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "UPDATE Ride SET destination=?, distance=?, time=?, fuelUsed=?, rideDate=?, bikeId=? WHERE rideId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
            	pstmt.setString(1, destination);
            	pstmt.setFloat(2, distance);
            	pstmt.setInt(3, duration);
            	pstmt.setInt(4, fuelUsed);
                pstmt.setDate(5, rideDate);
                pstmt.setInt(6, bikeId);
                pstmt.setInt(7, rideId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " ride updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating ride: " + e.getMessage());
        }
    }

    // Delete Ride by rideId
    private static void deleteRide(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Ride ID to delete: ");
            int rideId = Integer.parseInt(sc.nextLine());

            String sql = "DELETE FROM Ride WHERE rideId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, rideId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " ride deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting ride: " + e.getMessage());
        }
    }
}
