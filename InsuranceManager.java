package ridetracker;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

// Manages CRUD operations for Insurance table
public class InsuranceManager {

	// Method to manage Insurance records
    public static void manageInsurance(Scanner sc, Connection conn) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Manage Insurance Records ---");
            System.out.println("1. Add New Insurance");
            System.out.println("2. View All Insurance");
            System.out.println("3. Update Insurance");
            System.out.println("4. Delete Insurance");
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
					addInsurance(sc, conn); // Create new Insurance
					break;
					
                case 2: 
					viewInsurance(conn); // Read all Insurances
					break;
					
                case 3: 
					updateInsurance(sc, conn); // Update Insurance
					break;
					
                case 4: 
					deleteInsurance(sc, conn); // Delete Insurance
					break;
					
                case 5: 
					System.out.println("Returning to Main Menu..."); 
					break;
					
                default: 
					System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Add new Insurance record
    private static void addInsurance(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Provider: ");
            String provider = sc.nextLine();

            System.out.print("Enter Policy Number: ");
            String policyNumber = sc.nextLine();

            System.out.print("Enter Start Date (YYYY-MM-DD): ");
            Date startDate = Date.valueOf(sc.nextLine());

            System.out.print("Enter End Date (YYYY-MM-DD): ");
            Date endDate = Date.valueOf(sc.nextLine());

            System.out.print("Enter Premium Amount: ");
            double premium = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "INSERT INTO Insurance(provider, policyNumber, startDate, endDate, premium, bikeId) VALUES (?, ?, ?, ?, ?, ?)";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, provider);
                pstmt.setString(2, policyNumber);
                pstmt.setDate(3, startDate);
                pstmt.setDate(4, endDate);
                pstmt.setDouble(5, premium);
                pstmt.setInt(6, bikeId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " insurance record added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding insurance: " + e.getMessage());
        }
    }

    // View all Insurances
    private static void viewInsurance(Connection conn) {
        try {
            String sql = "SELECT * FROM Insurance";
            try (var stmt = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {
                System.out.println("\nAll Insurance Records:");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("insuranceId") + " | " +
                        rs.getString("provider") + " | No: " +
                        rs.getString("policyNumber") + " | From: " +
                        rs.getDate("startDate") + " | To: " +
                        rs.getDate("endDate") + " | " +
                        rs.getDouble("premium") + " Rs. | Bike ID: " +
                        rs.getInt("bikeId")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving insurance: " + e.getMessage());
        }
    }

    // Update Insurance by insuranceId
    private static void updateInsurance(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Insurance ID to update: ");
            int insuranceId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new Provider: ");
            String provider = sc.nextLine();

            System.out.print("Enter new Policy Number: ");
            String policyNumber = sc.nextLine();

            System.out.print("Enter new Start Date (YYYY-MM-DD): ");
            Date startDate = Date.valueOf(sc.nextLine());

            System.out.print("Enter new End Date (YYYY-MM-DD): ");
            Date endDate = Date.valueOf(sc.nextLine());

            System.out.print("Enter new Premium: ");
            double premium = Double.parseDouble(sc.nextLine());

            System.out.print("Enter new Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "UPDATE Insurance SET provider=?, policyNumber=?, startDate=?, endDate=?, premium=?, bikeId=? WHERE insuranceId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, provider);
                pstmt.setString(2, policyNumber);
                pstmt.setDate(3, startDate);
                pstmt.setDate(4, endDate);
                pstmt.setDouble(5, premium);
                pstmt.setInt(6, bikeId);
                pstmt.setInt(7, insuranceId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " insurance record updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating insurance: " + e.getMessage());
        }
    }

    // Delete Insurance by insuranceId
    private static void deleteInsurance(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Insurance ID to delete: ");
            int insuranceId = Integer.parseInt(sc.nextLine());

            String sql = "DELETE FROM Insurance WHERE insuranceId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, insuranceId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " insurance record deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting insurance: " + e.getMessage());
        }
    }
}
