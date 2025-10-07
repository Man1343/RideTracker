package ridetracker;

import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;

// Manage CRUD for Service table
public class ServiceManager {

	// Method to manage Service related operations
    public static void manageService(Scanner sc, Connection conn) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Manage Service Records ---");
            System.out.println("1. Add New Service");
            System.out.println("2. View All Services");
            System.out.println("3. Update Service");
            System.out.println("4. Delete Service");
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
                	addService(sc, conn); // Create new Service
                	break;
                	
                case 2: 
                	viewServices(conn); // Read all Services
                	break;
                	
                case 3: 
                	updateService(sc, conn); // Update Service
                	break;
                	
                case 4: 
                	deleteService(sc, conn); // Delete Service
                	break;
                	
                case 5: 
                	System.out.println("Returning to Main Menu..."); 
                	break;
                	
                default: 
                	System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Add new Service record
    private static void addService(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Service Type: ");
            String type = sc.nextLine();

            System.out.print("Enter Service Date (YYYY-MM-DD): ");
            Date date = Date.valueOf(sc.nextLine());

            System.out.print("Enter Service Cost: ");
            double cost = Double.parseDouble(sc.nextLine());

            System.out.print("Enter Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "INSERT INTO ServiceRecord(serviceType, serviceDate, cost, bikeId) VALUES (?, ?, ?, ?)";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, type);
                pstmt.setDate(2, date);
                pstmt.setDouble(3, cost);
                pstmt.setInt(4, bikeId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " service record added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding service: " + e.getMessage());
        }
    }

    // View all Services
    private static void viewServices(Connection conn) {
        try {
            String sql = "SELECT * FROM ServiceRecord";
            try (var stmt = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {
                System.out.println("\nAll Service Records:");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("serviceId") + " | " +
                        rs.getString("serviceType") + " service | " +
                        rs.getDate("serviceDate") + " | " +
                        rs.getDouble("cost") + " Rs. | Bike ID: " +
                        rs.getInt("bikeId")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving services: " + e.getMessage());
        }
    }

    // Update Service by serviceId
    private static void updateService(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Service ID to update: ");
            int serviceId = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new Service Type: ");
            String type = sc.nextLine();

            System.out.print("Enter new Service Date (YYYY-MM-DD): ");
            Date date = Date.valueOf(sc.nextLine());

            System.out.print("Enter new Cost: ");
            double cost = Double.parseDouble(sc.nextLine());

            System.out.print("Enter new Bike ID: ");
            int bikeId = Integer.parseInt(sc.nextLine());

            String sql = "UPDATE ServiceRecord SET serviceType=?, serviceDate=?, cost=?, bikeId=? WHERE serviceId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, type);
                pstmt.setDate(2, date);
                pstmt.setDouble(3, cost);
                pstmt.setInt(4, bikeId);
                pstmt.setInt(5, serviceId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " service record updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error updating service: " + e.getMessage());
        }
    }

    // Delete Service by serviceId
    private static void deleteService(Scanner sc, Connection conn) {
        try {
            System.out.print("Enter Service ID to delete: ");
            int serviceId = Integer.parseInt(sc.nextLine());

            String sql = "DELETE FROM ServiceRecord WHERE serviceId=?";
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, serviceId);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " service record deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting service: " + e.getMessage());
        }
    }
}
