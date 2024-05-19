package booking;

import java.util.List;

public class BookingEntity {
    private Integer bookingId;
    private Integer customerId;
    private String customerName;
    private Integer concertId;
    private Integer totalTickets;
    private List<TicketEntity> ticketEntities;

    public BookingEntity(Integer bookingId, Integer customerId, String customerName, Integer concertId, Integer totalTickets, List<TicketEntity> ticketEntities) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.concertId = concertId;
        this.totalTickets = totalTickets;
        this.ticketEntities = ticketEntities;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getConcertId() {
        return concertId;
    }

    public void setConcertId(Integer concertId) {
        this.concertId = concertId;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }

    public List<TicketEntity> getTicketEntities() {
        return ticketEntities;
    }

    public void setTicketEntities(List<TicketEntity> ticketEntities) {
        this.ticketEntities = ticketEntities;
    }
}
