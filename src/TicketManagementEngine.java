import booking.BookingDB;
import booking.BookingService;
import concert.ConcertDB;
import concert.ConcertService;
import customer.CustomerDB;
import customer.CustomerEntity;
import customer.CustomerService;
import utility.Utility;
import venue.VenueDB;
import venue.VenueService;

import java.io.FileNotFoundException;
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
    private static void validateCustomerFile(String customerFilePath) throws FileNotFoundException {
        CustomerDB.customerFilePath = customerFilePath;
        CustomerDB.readAllCustomers();
    }
    private static void validateConcertFile(String concertFilePath) throws FileNotFoundException {
        ConcertDB.concertFilePath = concertFilePath;
        ConcertDB.readAllConcerts();
    }
    private static void validateVenueFiles(String[] venueFilePaths) throws FileNotFoundException {
        VenueDB.venueFilePaths = venueFilePaths;
        VenueDB.readAllVenues();
    }
    private static void validateBookingFiles(String bookingFilePath) throws FileNotFoundException {
        BookingDB.bookingFilePath = bookingFilePath;
        BookingDB.readAllBookings();
    }

    private static void handleCustomer(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        Integer customerId = null;
        String customerPassword = null;
        CustomerEntity currentCustomer = null;
        CustomerService customerService = new CustomerService();
        ConcertService concertService = new ConcertService();
        VenueService venueService = new VenueService();
        BookingService bookingService = new BookingService();
        if(args[1].contains(".csv")){
            validateCustomerFile(args[1]);
            validateConcertFile(args[2]);
            validateBookingFiles(args[3]);
            validateVenueFiles(Utility.getSubArray(args, 4));
            System.out.print("Enter your name: ");
            String customerName = s.nextLine();
            System.out.print("Enter your password: ");
            customerPassword = s.nextLine();
            currentCustomer = customerService.createCustomer(customerName, customerPassword);
        }else {
            validateCustomerFile(args[3]);
            validateConcertFile(args[4]);
            validateBookingFiles(args[5]);
            validateVenueFiles(Utility.getSubArray(args, 6));
            customerId = Integer.parseInt(args[1]);
            customerPassword = args[2];
            currentCustomer = customerService.loginCustomer(customerId, customerPassword);
        }
        System.out.println(String.format("Welcome %s to Ticket Management System", currentCustomer.getName()));
        printBanner();
        Integer selectedConcertId = null;
        do{
            System.out.println("Select a concert or 0 to exit");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            concertService.showAllAvailableConcert();
            selectedConcertId = s.nextInt();
            if(selectedConcertId == 0) break;
            Integer selectedMenuOption = null;
            do{
                System.out.println("Select an option to get started!");
                System.out.println("Press 1 to look at the ticket costs");
                System.out.println("Press 2 to view seats layout");
                System.out.println("Press 3 to book seats");
                System.out.println("Press 4 to view booking details");
                System.out.println("Press 5 to exit");
                selectedMenuOption = s.nextInt();
                switch (selectedMenuOption){
                    case 1: {
                        concertService.showTicketCost(selectedConcertId);
                        break;
                    }
                    case 2: {
                        venueService.showVenueLayoutStatus(selectedConcertId);
                        break;
                    }
                    case 3: {
                        venueService.showVenueLayoutStatus(selectedConcertId);
                        bookingService.initiateBookingProcess(selectedConcertId, currentCustomer);
                        break;
                    }
                    case 4: {
                        bookingService.showBookingDetails(selectedConcertId, currentCustomer);
                        break;
                    }
                    default:
                        System.out.println("Invalid Input");
                }

            }while (selectedMenuOption != 5);
        }while (selectedConcertId != 0);
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
