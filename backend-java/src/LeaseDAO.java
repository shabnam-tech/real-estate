import java.sql.*;
import java.util.Scanner;

public class LeaseDAO {
    public static void insertLease(Scanner scanner) {
        System.out.print("Enter Lease ID: ");
        int leaseId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Start Date (yyyy-mm-dd): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter End Date (yyyy-mm-dd): ");
        String endDate = scanner.nextLine();

        System.out.print("Enter Property ID: ");
        int propertyId = scanner.nextInt();

        System.out.print("Enter Tenant ID: ");
        int tenantId = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call Lease_Management.Assign_Lease(?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?)}")) {

            stmt.setInt(1, leaseId);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setInt(4, propertyId);
            stmt.setInt(5, tenantId);

            stmt.execute();
            System.out.println("Lease added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAllLeases() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Lease")) {
    
            System.out.println("\n===== Lease Records =====");
            while (rs.next()) {
                int leaseId = rs.getInt("lease_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                int propertyId = rs.getInt("property_id");
                int tenantId = rs.getInt("tenant_id");
    
                System.out.println("Lease ID: " + leaseId);
                System.out.println("Start Date: " + startDate);
                System.out.println("End Date: " + endDate);
                System.out.println("Property ID: " + propertyId);
                System.out.println("Tenant ID: " + tenantId);
                System.out.println("--------------------------");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
