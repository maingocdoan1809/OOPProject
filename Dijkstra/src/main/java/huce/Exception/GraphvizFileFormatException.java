package huce.Exception;

public class GraphvizFileFormatException extends  Exception{
    public GraphvizFileFormatException(String message) {
        super(message);
    }
    public GraphvizFileFormatException() {
        super("Something wrong with your graph");
    }
}
