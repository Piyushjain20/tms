package venue;

import java.util.Map;

public class VenueEntity {
    private String name;
    private Map<String, Row> rows;
    private String[] layout;

    public VenueEntity(String name, Map<String, Row> rows, String[] layout) {
        this.name = name;
        this.rows = rows;
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Row> getRows() {
        return rows;
    }

    public void setRows(Map<String, Row> rows) {
        this.rows = rows;
    }

    public String[] getLayout() {
        return layout;
    }

    public void setLayout(String[] layout) {
        this.layout = layout;
    }
}
