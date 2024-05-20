package venue;

import exception.InvalidFormatException;
import utility.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VenueDB {
    public static String[] venueFilePaths;
    public static final Map<String, VenueEntity> availableVenue = new HashMap<>();

    public static VenueEntity parseVenueLayout(String venueName, String layoutText) {
        Map<String, Row> rows = new HashMap<>();
        String[] lines = layoutText.strip().split("\n");
        VenueEntity venue = new VenueEntity(venueName, rows, lines);

        for (String line : lines) {
            try{
                if(line.equals("")) continue;
                String[] parts = line.split("\\s+");
                String rowTypePrefix = parts[0].substring(0, 1);  // First character indicates the row type (V, S, T)
                if(!Utility.isParsableToInteger(parts[0].substring(1))) throw new InvalidFormatException("Invalid Row Number.");
                String rowType = "";
                switch (rowTypePrefix) {
                    case "V":
                        rowType = "VIP";
                        break;
                    case "S":
                        rowType = "SEATING";
                        break;
                    case "T":
                        rowType = "STANDING";
                        break;
                    default:
                        throw new InvalidFormatException("Invalid Zone Type.");
                }
                Map<Integer, Seat> seats = new HashMap<>();
                Row row = new Row(rowTypePrefix, rowType, seats);
                rows.put(parts[0], row);
                // TODO: can add exception handling at each seat number if text case fails
                Arrays.stream(parts[1].substring(1, parts[1].length() - 1).split("]\\["))
                        .forEach((seatNumber) -> {
                            seats.put(Integer.parseInt(seatNumber), new Seat(Integer.parseInt(seatNumber), "Left"));
                        });
                Arrays.stream(parts[2].substring(1, parts[2].length() - 1).split("]\\["))
                        .forEach((seatNumber) -> {
                            seats.put(Integer.parseInt(seatNumber), new Seat(Integer.parseInt(seatNumber), "Center"));
                        });

                Arrays.stream(parts[3].substring(1, parts[3].length() - 1).split("]\\["))
                        .forEach((seatNumber) -> {
                            seats.put(Integer.parseInt(seatNumber), new Seat(Integer.parseInt(seatNumber), "Right"));
                        });

            }catch (InvalidFormatException e){
                System.out.println(e.getMessage() + "  Skipping this line.");
            }
        }
        return venue;
    }

    public static void readAllVenues() throws FileNotFoundException {
        for (String filePath: venueFilePaths) {
            if(!Utility.validateFilePath(filePath)) throw new FileNotFoundException(String.format("%s (No such file or directory)", filePath));
            try{
                Path path = Paths.get(filePath);
                String venueLayout = new String(Files.readAllBytes(path));
                String venueFileName = path.getFileName().toString();
                String venueName = venueFileName.substring(venueFileName.indexOf('_') + 1,venueFileName.length() - 4);
                VenueEntity venue = parseVenueLayout(venueName, venueLayout);
                availableVenue.put(venueName, venue);
            }catch (IOException e){
                System.out.println("My Exception in reading venue file"+ e.getMessage());
            }
        }
    }

    public static boolean hasVenueForVenueName(String name){
        return availableVenue.containsKey(name);
    }

    public static VenueEntity getVenueByVenueName(String name){
        return availableVenue.get(name);
    }
}
