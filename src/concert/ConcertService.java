package concert;

import booking.BookingService;
import venue.VenueService;

public class ConcertService {

    public void showAllAvailableConcert(){
        VenueService venueService = new VenueService();
        BookingService bookingService = new BookingService();
        System.out.printf("%-5s%-15s%-15s%-15s%-30s%-15s%-15s%-15s%n",
                "#", "Date", "Artist Name", "Timing", "Venue Name",
                "Total Seats", "Seats Booked", "Seats Left");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for(ConcertEntity concert: ConcertDB.availableConcert.values()){
            int totalSeat = venueService.getTotalSeatCountByVenueName(concert.getVenueName());
            int totalBookedSeats = bookingService.getCountOfBookedTicketsByConcertId(concert.getId());
            System.out.printf("%-5s%-15s%-15s%-15s%-30s%-15s%-15s%-15s%n",
                    concert.getId(), concert.getDate(), concert.getArtistName(), concert.getStartTime(),
                    concert.getVenueName(),totalSeat, totalBookedSeats, totalSeat-totalBookedSeats);
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

    }

    public void showTicketCost(Integer selectedConcertId) {
        ConcertEntity selectedConcert = ConcertDB.availableConcert.get(selectedConcertId);
        System.out.printf("---------- %8s ----------%n","SEATING");
        System.out.printf("Left Seats: %s\n", selectedConcert.getZonePrices().get("SEATING").getLeftPrice());
        System.out.printf("Center Seats: %s\n", selectedConcert.getZonePrices().get("SEATING").getCenterPrice());
        System.out.printf("Right Seats: %s\n", selectedConcert.getZonePrices().get("SEATING").getRightPrice());
        System.out.println("------------------------------");
        System.out.printf("---------- %8s ----------%n","STANDING");
        System.out.printf("Left Seats: %s\n", selectedConcert.getZonePrices().get("STANDING").getLeftPrice());
        System.out.printf("Center Seats: %s\n", selectedConcert.getZonePrices().get("STANDING").getCenterPrice());
        System.out.printf("Right Seats: %s\n", selectedConcert.getZonePrices().get("STANDING").getRightPrice());
        System.out.println("------------------------------");
        System.out.printf("---------- %8s ----------%n","VIP");
        System.out.printf("Left Seats: %s\n", selectedConcert.getZonePrices().get("VIP").getLeftPrice());
        System.out.printf("Center Seats: %s\n", selectedConcert.getZonePrices().get("VIP").getCenterPrice());
        System.out.printf("Right Seats: %s\n", selectedConcert.getZonePrices().get("VIP").getRightPrice());
        System.out.println("------------------------------");
    }
}
