import java.sql.*;
import java.util.Scanner;

public class PaymentDAO {

    public static void insertPayment(Scanner scanner) {
        System.out.print("Enter Payment ID: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Payment Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Payment Method: ");
        String method = scanner.nextLine();

        System.out.print("Enter Lease ID: ");
        int leaseId = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call insert_payment_proc(?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)}")) {

            stmt.setInt(1, paymentId);
            stmt.setString(2, date);
            stmt.setDouble(3, amount);
            stmt.setString(4, method);
            stmt.setInt(5, leaseId);

            stmt.execute();
            System.out.println("✅ Payment added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAllPayments() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Payment")) {

            System.out.println("\n===== Payment Records =====");
            while (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                Date date = rs.getDate("payment_date");
                double amount = rs.getDouble("amount");
                String method = rs.getString("method");
                int leaseId = rs.getInt("lease_id");

                System.out.println("Payment ID: " + paymentId);
                System.out.println("Date: " + date);
                System.out.println("Amount: " + amount);
                System.out.println("Method: " + method);
                System.out.println("Lease ID: " + leaseId);
                System.out.println("--------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updatePayment(Scanner scanner) {
        System.out.print("Enter Payment ID to update: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Enter New Amount: ");
        double newAmount = scanner.nextDouble();
        scanner.nextLine();
    
        System.out.print("Enter New Method: ");
        String newMethod = scanner.nextLine();
    
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call update_payment_proc(?, ?, ?)}")) {
    
            stmt.setInt(1, paymentId);
            stmt.setDouble(2, newAmount);
            stmt.setString(3, newMethod);
    
            stmt.execute();
            System.out.println("✅ Payment updated successfully!");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deletePayment(Scanner scanner) {
        System.out.print("Enter Payment ID to delete: ");
        int paymentId = scanner.nextInt();
    
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call delete_payment_proc(?)}")) {
    
            stmt.setInt(1, paymentId);
    
            stmt.execute();
            System.out.println("✅ Payment deleted successfully!");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
