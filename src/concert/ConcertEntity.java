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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Map<String, ZonePricesEntity> getZonePrices() {
        return zonePrices;
    }

    public void setZonePrices(Map<String, ZonePricesEntity> zonePrices) {
        this.zonePrices = zonePrices;
    }
}
