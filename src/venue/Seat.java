package venue;

public class Seat {
    private Integer seatNumber;
    private String sectionType;

    public Seat(Integer seatNumber, String sectionType) {
        this.seatNumber = seatNumber;
        this.sectionType = sectionType;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }
}
