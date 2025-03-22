package exceptions;


/**
 * An exception for when the algorithm has no more positions to search
 **/
public class AlgoOutOfMoves extends RuntimeException {

    public AlgoOutOfMoves() {
        super("The algorithm cannot step since there is no possible path");
    }
    
}
