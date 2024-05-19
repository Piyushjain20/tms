package exception;

public class InvalidLineException extends Exception{
    public InvalidLineException() {
    }

    public InvalidLineException(String message) {
        super(message);
    }
}
