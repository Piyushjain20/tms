package booking;

import concert.ConcertDB;
import concert.ConcertEntity;
import customer.CustomerEntity;
import venue.VenueDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingService {

    public int getCountOfBookedTicketsByConcertId(Integer concertId){
        int totalTicketCountForConcert = 0;
        for(BookingEntity booking: BookingDB.availableBookings.values()){
            if(booking.getConcertId().equals(concertId)){
                totalTicketCountForConcert += booking.getTotalTickets();
            }
        }
        return totalTicketCountForConcert;
    }

    public void showBookingDetails(Integer selectedConcertId, CustomerEntity currentCustomer) {
        List<BookingEntity> bookings = BookingDB.availableBookings.values().stream()
                .filter((booking) -> booking.getConcertId().equals(selectedConcertId)
                        && booking.getCustomerId().equals(currentCustomer.getId())).toList();
        ConcertEntity selectedConcert = ConcertDB.availableConcert.get(selectedConcertId);
        String bookingFormat = "%-5s%-15s%-15s%-10s%-15s%-15s%-10s%n";
        String ticketFormat = "%-5s%-15s%-15s%-10s%-10s%n";
        System.out.println("Bookings");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(bookingFormat, "Id", "Concert Date", "Artist Name", "Timing", "Venue Name", "Seats Booked", "Total Price");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        bookings.forEach(booking -> {
            System.out.printf(bookingFormat, booking.getBookingId(), selectedConcert.getDate(), selectedConcert.getArtistName(),
                    selectedConcert.getStartTime(),selectedConcert.getVenueName(), booking.getTotalTickets(), booking.getTotalPrice());

        });
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Ticket Info");
        bookings.forEach(booking -> {
            System.out.printf("############### Booking Id: %s ####################\n", booking.getBookingId());
            System.out.printf(ticketFormat, "Id", "Aisle Number", "Seat Number", "Seat Type", "Price");
            System.out.println("##################################################");
            booking.getTicketEntities().forEach(ticket -> {
                System.out.printf(ticketFormat, ticket.getTicketId(), ticket.getRowNumber(), ticket.getSeatNumber(), ticket.getZoneType(), ticket.getPrice());
            });
            System.out.println("##################################################\n");
        });

    }
    private String zoneTypeFromRowId(String rowId){
        switch (rowId.charAt(0)){
            case 'V': return "VIP";
            case 'T': return "STANDING";
            case 'S': return "SEATING";
        }
        return null;
    }
    private

    public void initiateBookingProcess(Integer selectedConcertId, CustomerEntity currentCustomer) {
        Scanner s = new Scanner(System.in);
        ConcertEntity selectedConcert = ConcertDB.getConcertByConcertId(selectedConcertId);
        System.out.print("Enter the aisle number:");
        String rowId = s.nextLine();
        System.out.print("Enter the seat number:");
        Integer seatStartNumber = s.nextInt();
        System.out.print("Enter the number of seats to be booked:");
        Integer totalSeats = s.nextInt();
        List<TicketEntity> bookedTicket = new ArrayList<>();
        for(int i=1; i<totalSeats;i++){
            Integer rowNumber = Integer.parseInt(rowId.substring(1));
            Integer seatNumber = seatStartNumber + i -1;
            String zoneType = zoneTypeFromRowId(rowId);
            // TODO : need to complete this
        }
        BookingEntity newBooking = new BookingEntity(null, currentCustomer.getId(),
                currentCustomer.getName(), selectedConcertId, totalSeats, bookedTicket);
        BookingDB.createBooking(newBooking);
    }
}
