import java.util.Scanner;

public class LeaseMenu {
    public static void handleLeaseMenu(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("\n===== Lease Management =====");
            System.out.println("1. Add Lease");
            System.out.println("2. View All Leases");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    LeaseDAO.insertLease(scanner);
                    break;
                case 2:
                    LeaseDAO.viewAllLeases();  
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
