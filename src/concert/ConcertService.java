package concert;

import customer.CustomerEntity;

import java.util.HashMap;
import java.util.Map;

public class ConcertService {

    private static final int MIN_REQUIRED_DATA_POINTS = 8;
    private String concertFilePath;
    private static Map<Integer, ConcertEntity> availableConcert = new HashMap<>();

    public ConcertService(String concertFilePath) {
        this.concertFilePath = concertFilePath;
    }
    public String getConcertFilePath() {
        return concertFilePath;
    }
    public static Map<Integer, ConcertEntity> getAvailableConcert() {
        return availableConcert;
    }

    public
    public void showAllAvailableConcert(){
        System.out.printf("%-5s%-15s%-15s%-15s%-30s%-15s%-15s%-15s%n",
                "#", "Date", "Artist Name", "Timing", "Venue Name",
                "Total Seats", "Seats Booked", "Seats Left");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

    }
}
