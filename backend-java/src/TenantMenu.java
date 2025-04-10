import java.util.Scanner;

public class TenantMenu {
    public static void handleTenantMenu(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("\n===== Tenant Management =====");
            System.out.println("1. Add Tenant");
            System.out.println("2. Update Tenant");
            System.out.println("3. Delete Tenant");
            System.out.println("4. View All Tenants");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    TenantDAO.insertTenant(scanner);
                    break;
                case 2:
                    TenantDAO.updateTenant(scanner);
                    break;
                case 3:
                    TenantDAO.deleteTenant(scanner);
                    break;
                case 4:
                    TenantDAO.viewTenants();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
