package exceptions;


/**
 * An exception for when the algorithm has found the end position
 **/
public class AlgoFinished extends RuntimeException {

    public AlgoFinished() {
        super("The algorithm cannot step once it is finished");
    }
    
}
