import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== Real Estate Management System =====");
            System.out.println("1. Manage Tenants");
            System.out.println("2. Manage Properties");
            System.out.println("3. Manage Leases");
            System.out.println("4. Manage Payments");
            System.out.println("5. Manage Maintenance Requests");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    TenantMenu.handleTenantMenu(scanner);
                    break;
                case 2:
                    PropertyMenu.handlePropertyMenu(scanner);
                    break;
                case 3:
                    LeaseMenu.handleLeaseMenu(scanner);
                    break;
                case 4:
                    PaymentMenu.handlePaymentMenu(scanner);
                    break;
                case 5:
                    MaintenanceMenu.handleMaintenanceMenu(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
