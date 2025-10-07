package ridetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// Manage CRUD operations for Bike table
public class BikeManager {
	
	// Method to manage Bike related operations
    public static void manageBike(Scanner sc, Connection conn) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Manage Bikes ---");
            System.out.println("1. Add New Bike");
            System.out.println("2. View All Bikes");
            System.out.println("3. Update Bike");
            System.out.println("4. Delete Bike");
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
                    addBike(sc, conn); // Create new Bike
                    break;

                case 2:
                    viewBikes(conn); // Read all Bikes
                    break;

                case 3:
                    updateBike(sc, conn); // Update Bike
                    break;

                case 4:
                    deleteBike(sc, conn); // Delete Bike
                    break;

                case 5:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Wrong input, try again.");
            }
        }
    }

    // Add new Bike to database
    private static void addBike(Scanner sc, Connection conn) {
        try {
        	System.out.println("Enter Bike Brand: ");
        	String brand = sc.nextLine();
            System.out.println("Enter Bike Model: ");
            String model = sc.nextLine();
            System.out.println("Enter Engine CC: ");
            int enginecc = Integer.parseInt(sc.nextLine());
            System.out.println("Enter Rider ID (Owner): ");
            int riderId = Integer.parseInt(sc.nextLine());

            String insertQuery = "INSERT INTO Bike(brand, model, engineCC, riderId) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, brand);
                pstmt.setString(2, model);
                pstmt.setInt(3, enginecc);
                pstmt.setInt(4, riderId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " bike added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding bike: " + e.getMessage());
        }
    }

    // View all Bikes from database
    private static void viewBikes(Connection conn) {
        try {
            String selectQuery = "SELECT * FROM Bike";
            try (java.sql.Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectQuery)) {
                System.out.println("\nAll Bikes:");
                while (rs.next()) {
                    System.out.println(rs.getInt("bikeId") + " | " +
                                       rs.getString("brand") + " | " +
                                       rs.getString("model") + " | " +
                                       rs.getInt("engineCC") + " | " +
                                       rs.getInt("riderId"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving bikes: " + e.getMessage());
        }
    }

    // Update Bike details by bikeId
    private static void updateBike(Scanner sc, Connection conn) {
        try {
            System.out.println("Enter Bike ID to Update: ");
            int bikeId = Integer.parseInt(sc.nextLine());
            System.out.println("Enter new Brand: ");
            String newBrand = sc.nextLine();
            System.out.println("Enter new Model: ");
            String newModel = sc.nextLine();
            System.out.println("Enter new Engine CC: ");
            int newEngineCC = Integer.parseInt(sc.nextLine());
            System.out.println("Enter new Rider ID: ");
            int newRiderId = Integer.parseInt(sc.nextLine());

            String updateQuery = "UPDATE Bike SET brand=?, model=?, engineCC=? ,riderId=? WHERE bikeId=?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setString(1, newBrand);
                pstmt.setString(2, newModel);
                pstmt.setInt(3, newEngineCC);
                pstmt.setInt(4, newRiderId);
                pstmt.setInt(5, bikeId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " bike updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating bike: " + e.getMessage());
        }
    }

    // Delete Bike by bikeId
    private static void deleteBike(Scanner sc, Connection conn) {
        try {
            System.out.println("Enter Bike ID to Delete: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String deleteQuery = "DELETE FROM Bike WHERE bikeId=?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, bikeId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " bike deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting bike: " + e.getMessage());
        }
    }
}
