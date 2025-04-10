import java.sql.*;
import java.util.Scanner;

public class MaintenanceDAO {
    
    public static void insertMaintenance(Scanner scanner) {
        System.out.print("Request ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Request Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Property ID: ");
        int propertyId = scanner.nextInt();

        System.out.print("Tenant ID: ");
        int tenantId = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call insert_maintenance_proc(?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)}")) {

            stmt.setInt(1, id);
            stmt.setString(2, date);
            stmt.setString(3, status);
            stmt.setString(4, desc);
            stmt.setInt(5, propertyId);
            stmt.setInt(6, tenantId);

            stmt.execute();
            System.out.println("✅ Maintenance request added!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMaintenance(Scanner scanner) {
        System.out.print("Request ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Status: ");
        String status = scanner.nextLine();

        System.out.print("New Description: ");
        String desc = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call update_maintenance_proc(?, ?, ?)}")) {

            stmt.setInt(1, id);
            stmt.setString(2, status);
            stmt.setString(3, desc);

            stmt.execute();
            System.out.println("✅ Maintenance request updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMaintenance(Scanner scanner) {
        System.out.print("Request ID to delete: ");
        int id = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call delete_maintenance_proc(?)}")) {

            stmt.setInt(1, id);

            stmt.execute();
            System.out.println("Maintenance request deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAllMaintenance() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Maintenance_Request")) {

            System.out.println("\n===== All Maintenance Requests =====");
            while (rs.next()) {
                System.out.printf("ID: %d | Date: %s | Status: %s | Description: %s | Property ID: %d | Tenant ID: %d%n",
                        rs.getInt("request_id"),
                        rs.getDate("request_date"),
                        rs.getString("status"),
                        rs.getString("description"),
                        rs.getInt("property_id"),
                        rs.getInt("tenant_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
