package huce.Exception;

public class NoDataException extends Exception{
    public NoDataException(String message) {
        super(message);
    }
    public NoDataException() {
        this("Empty graph!");
    }
}
