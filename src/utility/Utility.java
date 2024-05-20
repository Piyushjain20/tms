package utility;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utility {
    public static boolean validateFilePath(String filePath){
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
    public static boolean isParsableToInteger(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        String regex = "^-?\\d+$";
        return s.matches(regex);
    }
    public static boolean isValidDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static String[] getSubArray(String[] array, int startIndex) {
        int subArrayLength = array.length - startIndex;
        String [] subArray = new String[subArrayLength];
        System.arraycopy(array, startIndex, subArray, 0, subArrayLength);
        return subArray;
    }
}
