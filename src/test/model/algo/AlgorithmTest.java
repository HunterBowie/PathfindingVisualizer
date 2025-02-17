package model.algo;


import model.Graph;
import model.Position;

public abstract class AlgorithmTest {
    
    protected Algorithm algo;
    protected Graph graph;
    protected Position startPos;
    protected Position endPos;

    public void runBefore() {
        startPos = new Position(0, 0);
        endPos = new Position(3, 3);
        graph = new Graph(4, 4, startPos, endPos);
    }

    public abstract void testConstructor();

}
