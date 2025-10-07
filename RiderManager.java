package ridetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// Manage CRUD operation for Rider table
public class RiderManager {
	
	// Method to manage Rider related operations
    public static void manageRider(Scanner sc, Connection conn) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Manage Riders ---");
            System.out.println("1. Add New Rider");
            System.out.println("2. View All Riders");
            System.out.println("3. Update Rider");
            System.out.println("4. Delete Rider");
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
                    addRider(sc, conn); // Create new rider
                    break;

                case 2:
                    viewRiders(conn); // Read all riders
                    break;

                case 3:
                    updateRider(sc, conn); // Update rider
                    break;

                case 4:
                    deleteRider(sc, conn); // Delete rider
                    break;

                case 5:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Wrong input, try again.");
            }
        }
    }

    // Add new Rider to database
    private static void addRider(Scanner sc, Connection conn) {
        try {
            System.out.println("Enter Rider Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Rider Email: ");
            String email = sc.nextLine();
            System.out.println("Enter Rider Phone Number: ");
            String phone = sc.nextLine();

            String insertQuery = "INSERT INTO Rider(name, email, phone) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, phone);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " rider added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding rider: " + e.getMessage());
        }
    }

    // View all Riders from database
    private static void viewRiders(Connection conn) {
        try {
            String selectQuery = "SELECT * FROM Rider";
            try (java.sql.Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectQuery)) {
                System.out.println("\nAll Riders:");
                while (rs.next()) {
                    System.out.println(rs.getInt("riderId") + " | " +
                                       rs.getString("name") + " | " +
                                       rs.getString("email") + " | " +
                                       rs.getString("phone"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving riders: " + e.getMessage());
        }
    }

    // Update Rider details by riderId
    private static void updateRider(Scanner sc, Connection conn) {
        try {
            System.out.println("Enter Rider ID to Update: ");
            int riderId = Integer.parseInt(sc.nextLine());
            System.out.println("Enter new name: ");
            String newName = sc.nextLine();
            System.out.println("Enter new email: ");
            String newEmail = sc.nextLine();
            System.out.println("Enter new phone number: ");
            String newPhone = sc.nextLine();

            String updateQuery = "UPDATE Rider SET name=?, email=?, phone=? WHERE riderId=?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setString(1, newName);
                pstmt.setString(2, newEmail);
                pstmt.setString(3, newPhone);
                pstmt.setInt(4, riderId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " rider updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating rider: " + e.getMessage());
        }
    }

    // Delete Rider by riderId
    private static void deleteRider(Scanner sc, Connection conn) {
        try {
            System.out.println("Enter Rider ID to Delete: ");
            int riderId = Integer.parseInt(sc.nextLine());

            String deleteQuery = "DELETE FROM Rider WHERE riderId=?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, riderId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " rider deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting rider: " + e.getMessage());
        }
    }
}
