package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGraph {

    private Graph testGraph;
    private Position startPos;
    private Position endPos;

    @BeforeEach
    public void runBefore() {
        startPos = new Position(0, 0);
        endPos = new Position(3, 2);

        testGraph = new Graph(5, 6, startPos, endPos);
    }

    @Test
    public void constructorTest() {
        assertEquals(testGraph.getRows(), 5);
        assertEquals(testGraph.getCols(), 6);
        assertEquals(testGraph.getStartPos(), startPos);
        assertEquals(testGraph.getEndPos(), endPos);
    }

    @Test
    public void getNodeTest() {
        assertEquals(testGraph.getNode(new Position(1, 1)),
                NodeType.EMPTY);
        testGraph.setNode(new Position(0, 1), NodeType.CLOSED);
        assertEquals(testGraph.getNode(new Position(0, 1)),
                NodeType.CLOSED);
        assertEquals(testGraph.getNode(new Position(4, 5)),
                NodeType.EMPTY);
        testGraph.setNode(new Position(4, 5), NodeType.OPEN);
        assertEquals(testGraph.getNode(new Position(4, 5)),
                NodeType.OPEN);
        assertEquals(testGraph.getNode(new Position(0, 0)),
                NodeType.EMPTY);
        testGraph.setNode(new Position(0, 0), NodeType.OPEN);
        assertEquals(testGraph.getNode(new Position(0, 0)),
                NodeType.OPEN);

    }

    @Test
    public void setNodeTest() {
        assertEquals(testGraph.getNode(new Position(1, 1)),
                NodeType.EMPTY);
        testGraph.setNode(new Position(0, 1), NodeType.CLOSED);
        assertEquals(testGraph.getNode(new Position(0, 1)),
                NodeType.CLOSED);
        assertEquals(testGraph.getNode(new Position(4, 5)),
                NodeType.EMPTY);
        testGraph.setNode(new Position(4, 5), NodeType.OPEN);
        assertEquals(testGraph.getNode(new Position(4, 5)),
                NodeType.OPEN);
        assertEquals(testGraph.getNode(new Position(0, 0)),
                NodeType.EMPTY);
        testGraph.setNode(new Position(0, 0), NodeType.OPEN);
        assertEquals(testGraph.getNode(new Position(0, 0)),
                NodeType.OPEN);
    }

}
