package exceptions;

public class AlgoOutOfMoves extends RuntimeException {

    public AlgoOutOfMoves() {
        super("The algorithm cannot step since there is no possible path");
    }
    
}
