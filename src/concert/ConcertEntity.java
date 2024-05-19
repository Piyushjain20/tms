package concert;

import java.util.HashMap;
import java.util.Map;

public class ConcertEntity {
    private Integer id;
    private String date;
    private Integer startTime;
    private String artistName;
    private String venueName;
    private Map<String, ZonePricesEntity> zonePrices;

    public ConcertEntity(Integer id, String date, Integer startTime, String artistName, String venueName, Map<String, ZonePricesEntity> zonePrices) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.artistName = artistName;
        this.venueName = venueName;
        this.zonePrices = zonePrices;
    }

    public static ConcertEntity parseRow(String[] columns) {
        int id = Integer.parseInt(columns[0]);
        String date = columns[1];
        int time = Integer.parseInt(columns[2]);
        String artist = columns[3];
        String venue = columns[4];
        Map<String, ZonePricesEntity> zonePrices = ConcertEntity.parseZonePrices(columns[5], columns[6], columns[7]);
        return new ConcertEntity(id, date, time, artist, venue, zonePrices);
    }
    private static Map<String, ZonePricesEntity> parseZonePrices(String standing, String seating, String vip) {
        Map<String, ZonePricesEntity> zonePrices = new HashMap<>();
        zonePrices.put("STANDING", ConcertEntity.parseZonePrice(standing));
        zonePrices.put("SEATING", ConcertEntity.parseZonePrice(seating));
        zonePrices.put("VIP", ConcertEntity.parseZonePrice(vip));
        return zonePrices;
    }
    private static ZonePricesEntity parseZonePrice(String priceString) {
        String[] tokens = priceString.split(":");
        int leftPrice = Integer.parseInt(tokens[1]);
        int centerPrice = Integer.parseInt(tokens[2]);
        int rightPrice = Integer.parseInt(tokens[3]);
        return new ZonePricesEntity(leftPrice, centerPrice, rightPrice);
    }
}
