package venue;

import booking.BookingDB;
import booking.BookingEntity;
import concert.ConcertDB;
import concert.ConcertEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VenueService {

    public Integer getTotalSeatCountByVenueName(String name){
        String key = name.toLowerCase().replace(' ', '_');
        String defaultKey = "default";
        Integer seatsCount = 0;
        if(VenueDB.availableVenue.containsKey(key)){
            VenueEntity venue = VenueDB.availableVenue.get(key);
            for(Row r: venue.getRows().values()){
                seatsCount += r.getSeats().keySet().size();
            }
            return seatsCount;
        }
        VenueEntity venue = VenueDB.availableVenue.get(defaultKey);
        for(Row r: venue.getRows().values()){
            seatsCount += r.getSeats().keySet().size();
        }
        return seatsCount;
    }

    private static String fillSeats(String s, HashSet<Integer> hashSet) {
        Pattern pattern = Pattern.compile("\\[(\\d+)\\]");
        Matcher matcher = pattern.matcher(s);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            if (hashSet.contains(number)) {
                matcher.appendReplacement(sb, "[X]");
            } else {
                matcher.appendReplacement(sb, matcher.group());
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public void showVenueLayoutStatus(Integer selectedConcertId) {
        ConcertEntity selectedConcert = ConcertDB.availableConcert.get(selectedConcertId);
        VenueEntity venue = VenueDB.getVenueByVenueName(selectedConcert.getVenueName().toLowerCase());
        List<BookingEntity> bookings = BookingDB.availableBookings.values()
                .stream().filter(booking -> booking.getConcertId().equals(selectedConcertId)).toList();
        Map<String, HashSet<Integer>> occupiedRowIdToSeatNumberMap = new HashMap<>();
        bookings.forEach(booking -> {
            booking.getTicketEntities().forEach(ticket -> {
                HashSet<Integer> occupiedSeats = occupiedRowIdToSeatNumberMap.getOrDefault(ticket.getRowId(), new HashSet<>());
                occupiedSeats.add(ticket.getSeatNumber());
                occupiedRowIdToSeatNumberMap.put(ticket.getRowId(), occupiedSeats);
            });
        });
        for (String line : venue.getLayout()) {
            if(line.equals("")){
                System.out.println();
                continue;
            }
            String[] parts = line.split("\\s+");
            String rowId = parts[0];
            if(occupiedRowIdToSeatNumberMap.containsKey(rowId)){
                String occupiedRow = fillSeats(line, occupiedRowIdToSeatNumberMap.get(rowId));
                System.out.println(occupiedRow);
            }else {
                System.out.println(line);
            }
        }
    }
}
