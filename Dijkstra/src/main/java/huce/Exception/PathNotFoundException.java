package huce.Exception;

public class PathNotFoundException extends Exception {
    public PathNotFoundException() {
        this("Cannot find a path in the given Graph.");
    }
    public PathNotFoundException(String message) {
        super(message);
    }
}
