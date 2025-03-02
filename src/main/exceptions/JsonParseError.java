package exceptions;

public class JsonParseError extends RuntimeException {

    public JsonParseError(String msg) {
        super(msg);
    }
    
}
