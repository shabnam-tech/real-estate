import java.sql.*;
import java.util.Scanner;

public class PropertyDAO {

    public static void insertProperty(Scanner scanner) {
        System.out.print("Enter Property ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Status: ");
        String status = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Type: ");
        String type = scanner.nextLine();

        String sql = "INSERT INTO Property (property_id, name, status, location, type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, status);
            pstmt.setString(4, location);
            pstmt.setString(5, type);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " property(s) added!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProperty(Scanner scanner) {
        System.out.print("Enter Property ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Status: ");
        String status = scanner.nextLine();
        System.out.print("Enter New Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter New Type: ");
        String type = scanner.nextLine();

        String sql = "UPDATE Property SET name = ?, status = ?, location = ?, type = ? WHERE property_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, status);
            pstmt.setString(3, location);
            pstmt.setString(4, type);
            pstmt.setInt(5, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " property(s) updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProperty(Scanner scanner) {
        System.out.print("Enter Property ID to Delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Property WHERE property_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " property(s) deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewProperties() {
        String sql = "SELECT * FROM Property";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n===== Property List =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("property_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("Location: " + rs.getString("location"));
                System.out.println("Type: " + rs.getString("type"));
                System.out.println("--------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
