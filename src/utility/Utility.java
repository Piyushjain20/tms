package utility;

import java.io.File;

public class Utility {
    public static boolean validateFilePath(String filePath){
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
}
