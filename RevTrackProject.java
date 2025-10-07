package ridetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class RevTrackProject {
    
	// Establish connection with MySQL database
    private static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/BikeManagementSystem?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        
        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Return connection object
        return DriverManager.getConnection(url, user, password);
    }
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        try ( Connection conn = getConnection()) {
            System.out.println("Database connected successfully.");
            
            int mainChoice = 0;
            
            // Main loop until user choose Exit
            while(mainChoice != 7) {
                System.out.println("\n------ Ride Tracking System ------");
                System.out.println("1. Manage Riders");
                System.out.println("2. Manage Bikes");
                System.out.println("3. Manage Rides");
                System.out.println("4. Manage Service Records");
                System.out.println("5. Manage Insurance Details");
                System.out.println("6. Manage Add-ons");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                
                try {
                    mainChoice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue; // Allow to re-enter if wrong input is given
                }
                
                // Each case connects to manager class
                switch (mainChoice) {
                    case 1:
                    	RiderManager.manageRider(sc, conn);
                        break;
                    case 2:
                        BikeManager.manageBike(sc, conn);
                        break;
                    case 3:
                        RideManager.manageRide(sc, conn);
                        break;
                    case 4:
                        ServiceManager.manageService(sc, conn);
                        break;
                    case 5:
                        InsuranceManager.manageInsurance(sc, conn);
                        break;
                    case 6:
                        AddOnsManager.manageAddons(sc, conn);
                        break;
                    case 7:
                        System.out.println("Exiting program. Bye Bye...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        sc.close();
    }

}
