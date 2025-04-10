import java.util.Scanner;

public class PropertyMenu {
    public static void handlePropertyMenu(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("\n===== Property Management =====");
            System.out.println("1. Add Property");
            System.out.println("2. Update Property");
            System.out.println("3. Delete Property");
            System.out.println("4. View All Properties");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    PropertyDAO.insertProperty(scanner);
                    break;
                case 2:
                    PropertyDAO.updateProperty(scanner);
                    break;
                case 3:
                    PropertyDAO.deleteProperty(scanner);
                    break;
                case 4:
                    PropertyDAO.viewProperties();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
