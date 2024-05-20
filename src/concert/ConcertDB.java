package concert;

import exception.InvalidFormatException;
import exception.InvalidLineException;
import utility.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConcertDB {

    private static final int MIN_REQUIRED_DATA_POINTS = 8;
    public static String concertFilePath;
    public static final Map<Integer, ConcertEntity> availableConcert = new HashMap<>();


    private static ConcertEntity parseRow(String[] columns) throws InvalidLineException, InvalidFormatException{
        if(columns.length < MIN_REQUIRED_DATA_POINTS) throw new InvalidLineException("Invalid Customer Files.");
        if(!Utility.isParsableToInteger(columns[0])) throw new InvalidFormatException("Concert Id is in incorrect format.");
        if(!Utility.isValidDate(columns[1])) throw new InvalidFormatException("Concert Date is in incorrect format");
        int id = Integer.parseInt(columns[0]);
        String date = columns[1];
        // TODO: can do validation on time part here
        int time = Integer.parseInt(columns[2]);
        String artist = columns[3];
        String venue = columns[4];
        Map<String, ZonePricesEntity> zonePrices = ConcertDB.parseZonePrices(new String[]{columns[5], columns[6], columns[7]});
        return new ConcertEntity(id, date, time, artist, venue, zonePrices);
    }

    public static boolean isValidPriceString(String s) {
        String pattern = "^(VIP|SEATING|STANDING):\\d+:\\d+:\\d+$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(s);
        return matcher.matches();
    }
    private static Map<String, ZonePricesEntity> parseZonePrices(String prices[]) throws InvalidFormatException {
        Map<String, ZonePricesEntity> zonePrices = new HashMap<>();
        for (String price: prices) {
            if(!isValidPriceString(price)){
                throw new InvalidFormatException("Concert Zone Type with price is in incorrect format.");
            }
            zonePrices.put(price.substring(0, price.indexOf(':')) , ConcertDB.parseZonePrice(price));
        }
        return zonePrices;
    }

    private static ZonePricesEntity parseZonePrice(String priceString) {
        // TODO: Parsing float from string might need exception handling
        String[] tokens = priceString.split(":");
        float leftPrice = Float.parseFloat(tokens[1]);
        float centerPrice = Float.parseFloat(tokens[2]);
        float rightPrice = Float.parseFloat(tokens[3]);
        return new ZonePricesEntity(leftPrice, centerPrice, rightPrice);
    }

    public static void readAllConcerts() throws FileNotFoundException {
        if(!Utility.validateFilePath(concertFilePath)) {
            throw new FileNotFoundException(String.format("%s (No such file or directory)", concertFilePath));
        }
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(concertFilePath));
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(",");
                try{
                    ConcertEntity c = ConcertDB.parseRow(fields);
                    availableConcert.put(c.getId(), c);
                }catch (InvalidLineException | InvalidFormatException e){
                    System.out.println(e.getMessage() + "  Skipping this line.");
                }
            }
        }catch (IOException e){
            System.out.println("My Exception in reading concert file"+ e.getMessage());
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("My Exception in closing concert reader connection"+e.getMessage());
                }
            }
        }
    }

    public static boolean hasConcertForConcertId(Integer id){
        return availableConcert.containsKey(id);
    }

    public static ConcertEntity getConcertByConcertId(Integer id){
        return availableConcert.get(id);
    }
}
