package venue;

import java.util.Map;

public class Row {
    private String rowId;
    private String rowType;
    private Map<Integer, Seat> seats;

    public Row(String rowId, String rowType, Map<Integer, Seat> seats) {
        this.rowId = rowId;
        this.rowType = rowType;
        this.seats = seats;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    public Map<Integer, Seat> getSeats() {
        return seats;
    }

    public void setSeats(Map<Integer, Seat> seats) {
        this.seats = seats;
    }
}
