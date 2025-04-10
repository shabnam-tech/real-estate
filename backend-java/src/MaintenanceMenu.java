import java.util.Scanner;

public class MaintenanceMenu {
    public static void handleMaintenanceMenu(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("\n===== Maintenance Management =====");
            System.out.println("1. Add Maintenance Request");
            System.out.println("2. View All Requests");
            System.out.println("3. Update Request");
            System.out.println("4. Delete Request");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    MaintenanceDAO.insertMaintenance(scanner);
                    break;
                case 2:
                    MaintenanceDAO.viewAllMaintenance();
                    break;
                case 3:
                    MaintenanceDAO.updateMaintenance(scanner);
                    break;
                case 4:
                    MaintenanceDAO.deleteMaintenance(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
