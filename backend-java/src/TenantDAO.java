import java.sql.*;
import java.util.Scanner;

public class TenantDAO {

    public static void insertTenant(Scanner scanner) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Tenant ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Contact Number: ");
            String contact = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            String sql = "INSERT INTO Tenant (tenant_id, name, contact_number, email) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, contact);
            pstmt.setString(4, email);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " tenant(s) added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTenant(Scanner sc) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Tenant ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter New Name: ");
            String newName = sc.nextLine();
        
            System.out.print("Enter New Contact Number: ");
            String newContact = sc.nextLine();
        
            System.out.print("Enter New Email: ");
            String newEmail = sc.nextLine();
            String sql = "UPDATE Tenant SET name = ?, contact_number = ?, email = ? WHERE tenant_id = ?";
        
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, newName);
            pstmt.setString(2, newContact);
            pstmt.setString(3, newEmail);
            pstmt.setInt(4, id);
        
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " tenant(s) updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTenant(Scanner scanner) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter Tenant ID to Delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM Tenant WHERE tenant_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " tenant(s) deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewTenants() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Tenant";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nID | Name | Contact | Email");
            System.out.println("----------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt("tenant_id") + " | " + rs.getString("name") + " | " + rs.getString("contact_number") + " | " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
