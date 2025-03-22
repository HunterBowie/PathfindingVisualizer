package exceptions;


/**
 * An exception for when the JSON data contains invalid values
 **/
public class JsonParseError extends RuntimeException {

    public JsonParseError(String msg) {
        super(msg);
    }
    
}
