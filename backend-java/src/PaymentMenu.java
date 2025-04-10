import java.util.Scanner;

public class PaymentMenu {
    public static void handlePaymentMenu(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("1. Add Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Update Payment");
            System.out.println("4. Delete Payment");
            System.out.println("5. Back to Main Menu");            
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    PaymentDAO.insertPayment(scanner);
                    break;
                case 2:
                    PaymentDAO.viewAllPayments();
                    break;
                case 3:
                    PaymentDAO.updatePayment(scanner);
                    break;
                case 4:
                    PaymentDAO.deletePayment(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }            
        }
    }
}
