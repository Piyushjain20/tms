import customer.CustomerEntity;
import customer.CustomerService;

import java.util.Scanner;

public class TicketManagementEngine {
    public static void main(String[] args) {
        try {
            switch (args[0]){
                case "--admin":
                    handleAdmin(args);
                    break;
                case "--customer":
                    handleCustomer(args);
                    break;
                default:
                    System.out.println("Invalid user mode. Terminating program now.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static void handleCustomer(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        Integer customerId = null;
        String customerPassword = null;
        String customerFilePath = null;
        CustomerService customerService = null;
        CustomerEntity currentCustomer = null;
        if(args[1].contains(".csv")){
            customerFilePath = args[1];
            System.out.print("Enter your name: ");
            String customerName = s.nextLine();
            System.out.print("Enter your password: ");
            customerPassword = s.nextLine();
            customerService = new CustomerService(customerFilePath);
            customerService.readAllCustomers();
            currentCustomer = customerService.createCustomer(customerName, customerPassword);
        }else {
            customerId = Integer.parseInt(args[1]);
            customerPassword = args[2];
            customerFilePath = args[3];
            customerService = new CustomerService(customerFilePath);
            customerService.readAllCustomers();
            currentCustomer = customerService.loginCustomer(customerId, customerPassword);
        }
        System.out.println(String.format("Welcome %s to Ticket Management System", currentCustomer.getName()));
        printBanner();
        Integer selectedInput = null;
        while (1){
            System.out.println("Select a concert or 0 to exit");
            System.out.println("----------------------------------------------------------------------------------- ----------------------------------------");
            // show available concert details
            selectedInput = s.nextInt();
            if(selectedInput == 0) break;
            //get selected concert object
            System.out.println("Select an option to get started!");
            System.out.println("Press 1 to look at the ticket costs");
            System.out.println("Press 2 to view seats layout");
            System.out.println("Press 3 to book seats");
            System.out.println("Press 4 to view booking details");
            System.out.println("Press 5 to exit");

        };
        System.out.println("Exiting customer mode");
    }

    private static void handleAdmin(String[] args) {
    }

    private static void printBanner(){
        System.out.println(" ________  ___ _____");
        System.out.println("|_   _|  \\/  |/  ___|");
        System.out.println("  | | | .  . |\\ `--.");
        System.out.println("  | | | |\\/| | `--. \\");
        System.out.println("  | | | |  | |/\\__/ /");
        System.out.println("  \\_/ \\_|  |_/\\____/");
    }
}
