package booking;

public class TicketEntity {
    private Integer ticketId;
    private Integer rowNumber;
    private Integer seatNumber;
    private String zoneType;
    private Float price;

    public TicketEntity(Integer ticketId, Integer rowNumber, Integer seatNumber, String zoneType, Float price) {
        this.ticketId = ticketId;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.zoneType = zoneType;
        this.price = price;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getRowId(){
        switch (this.zoneType){
            case "VIP" : return String.format("V%s",this.rowNumber);
            case "SEATING" : return String.format("S%s",this.rowNumber);
            case "STANDING" : return String.format("T%s",this.rowNumber);
        }
        return null;
    }
}
