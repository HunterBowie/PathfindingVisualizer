package exceptions;

public class AlgoFinished extends RuntimeException {

    public AlgoFinished() {
        super("The algorithm cannot step once it is finished");
    }
    
}
