package model.algo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.AlgoFinished;
import exceptions.AlgoOutOfMoves;
import model.Graph;
import model.Node;
import model.NodeType;
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

    @Test
    public void testConstructor() {
        assertEquals(graph, algo.getGraph());
        assertFalse(algo.isFinished()); 
    }


    @Test
    public void testNoMoves() {
        graph.setNode(new Position(0, 1), new Node(NodeType.WALL));
        graph.setNode(new Position(1, 1), new Node(NodeType.WALL));
        graph.setNode(new Position(1, 0), new Node(NodeType.WALL));
        algo.step();
        try {
            algo.step();
            fail();
        } catch (AlgoOutOfMoves e) {

        }
    }

    @Test
    public void testFinished() {
        while (!algo.isFinished()) {
            algo.step();
        }
        try {
            algo.step();
            fail();
        } catch (AlgoFinished e) {

        }
    }

}
