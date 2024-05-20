package booking;

import exception.InvalidFormatException;
import exception.InvalidLineException;
import utility.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDB {

    private static final int MIN_REQUIRED_DATA_POINTS = 5;
    public static String bookingFilePath;
    public static final Map<Integer, BookingEntity> availableBookings = new HashMap<>();

    private static BookingEntity parseRow(String[] columns) throws InvalidLineException, InvalidFormatException {
        if(columns.length < MIN_REQUIRED_DATA_POINTS) throw new InvalidLineException("Invalid booking Files.");
        if(!Utility.isParsableToInteger(columns[0])) throw new InvalidFormatException("Booking Id is in incorrect format.");
        if(!Utility.isParsableToInteger(columns[1])) throw new InvalidFormatException("Customer Id is in incorrect format.");
        if(!Utility.isParsableToInteger(columns[3])) throw new InvalidFormatException("Concert Id is in incorrect format.");
        if(!Utility.isParsableToInteger(columns[4]) || Integer.parseInt(columns[4]) < 1) throw new InvalidFormatException("Incorrect Number of Tickets.");
        Integer bookingId = Integer.parseInt(columns[0]);
        Integer customerId = Integer.parseInt(columns[1]);
        String customerName = columns[2];
        Integer concertId = Integer.parseInt(columns[3]);
        Integer totalTickets = Integer.parseInt(columns[4]);
        List<TicketEntity> tickets = parseTickets(Utility.getSubArray(columns, 5));
        return new BookingEntity(bookingId, customerId, customerName, concertId, totalTickets, tickets);
    }
    private static List<TicketEntity> parseTickets(String[] columns) throws InvalidLineException, InvalidFormatException {
        if(columns.length % 5 != 0) throw new InvalidLineException("Invalid booking Files.");
        List<TicketEntity> tickets = new ArrayList<>();
        for(int i=0; i< columns.length; i+=5){
            if(!Utility.isParsableToInteger(columns[0])) throw new InvalidFormatException("Ticket Id is in incorrect format.");
            if(!Utility.isParsableToInteger(columns[1])) throw new InvalidFormatException("Row Number is in incorrect format.");
            if(!Utility.isParsableToInteger(columns[2])) throw new InvalidFormatException("Seat Number is in incorrect format.");
//            TODO: can add validations for zone and price of ticket
            Integer ticketId = Integer.parseInt(columns[i]);
            Integer rowNumber = Integer.parseInt(columns[i+1]);
            Integer seatNumber = Integer.parseInt(columns[i+2]);
            String zoneType = columns[i+3];
            Float price = Float.parseFloat(columns[i+4]);
            tickets.add(new TicketEntity(ticketId, rowNumber, seatNumber, zoneType, price));
        }
        return tickets;
    }

    public static void readAllBookings() throws FileNotFoundException {
        if(!Utility.validateFilePath(bookingFilePath)) {
            throw new FileNotFoundException(String.format("%s (No such file or directory)", bookingFilePath));
        }
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(bookingFilePath));
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                try{
                    BookingEntity booking = BookingDB.parseRow(fields);
                    availableBookings.put(booking.getBookingId(), booking);
                }catch (InvalidLineException | InvalidFormatException e){
                    System.out.println(e.getMessage() + "  Skipping this line.");
                }
            }
        }catch (IOException e){
            System.out.println("My Exception in reading booking file"+ e.getMessage());
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("My Exception in closing booking reader connection"+e.getMessage());
                }
            }
        }
    }

    public static BookingEntity createBooking(BookingEntity newBooking) {
    }
}
