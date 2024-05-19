package exception;

public class InvalidFormatException extends Exception{
    public InvalidFormatException() {
    }

    public InvalidFormatException(String message) {
        super(message);
    }
}
