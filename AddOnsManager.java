package ridetracker;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

// Manages CRUD for Addons table
public class AddOnsManager {

	// Method to manage Addons operations
    public static void manageAddons(Scanner sc, Connection conn) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Manage Add-ons ---");
            System.out.println("1. Add New Add-on");
            System.out.println("2. View All Add-ons");
            System.out.println("3. Update Add-on");
            System.out.println("4. Delete Add-on");
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
					addAddon(sc, conn); // Create new Addon
					break;
					
                case 2: 
					viewAddons(conn); // Read all Addons
					break;
					
                case 3: 
					updateAddon(sc, conn); // Update Addon
					break;
					
                case 4: 
					deleteAddon(sc, conn); // Delete Addon
					break;
					
                case 5: 
					System.out.println("Returning to Main Menu..."); 
					break;
					
                default: 
					System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Add new Add-on
    private static void addAddon(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Add-on Name: ");
            String addonName = sc.nextLine();

            System.out.print("Enter Price: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
            Date purchaseDate = Date.valueOf(sc.nextLine());

            System.out.print("Enter Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "INSERT INTO Addons(addonName, price, purchaseDate, bikeId) VALUES (?, ?, ?, ?)";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, addonName);
                pstmt.setDouble(2, price);
                pstmt.setDate(3, purchaseDate);
                pstmt.setInt(4, bikeId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " add-on added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding add-on: " + e.getMessage());
        }
    }

    // View all Add-ons
    private static void viewAddons(Connection conn) {
        try {
            String sql = "SELECT * FROM Addons";
            try (var stmt = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {
                System.out.println("\nAll Add-ons:");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("addonId") + " | " +
                        rs.getString("addonName") + " | " +
                        rs.getDouble("price") + " Rs. | " +
                        rs.getDate("purchaseDate") + " | Bike ID: " +
                        rs.getInt("bikeId")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving add-ons: " + e.getMessage());
        }
    }

    // Update Add-on by addonId
    private static void updateAddon(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Add-on ID to update: ");
            int addonId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new Add-on Name: ");
            String addonName = sc.nextLine();

            System.out.print("Enter new Price: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Enter new Purchase Date (YYYY-MM-DD): ");
            Date purchaseDate = Date.valueOf(sc.nextLine());

            System.out.print("Enter new Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "UPDATE Addons SET addonName=?, price=?, purchaseDate=?, bikeId=? WHERE addonId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, addonName);
                pstmt.setDouble(2, price);
                pstmt.setDate(3, purchaseDate);
                pstmt.setInt(4, bikeId);
                pstmt.setInt(5, addonId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " add-on updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating add-on: " + e.getMessage());
        }
    }

    // Delete Add-on by addonId
    private static void deleteAddon(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Add-on ID to delete: ");
            int addonId = Integer.parseInt(sc.nextLine());

            String sql = "DELETE FROM Addons WHERE addonId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, addonId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " add-on deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting add-on: " + e.getMessage());
        }
    }
}
