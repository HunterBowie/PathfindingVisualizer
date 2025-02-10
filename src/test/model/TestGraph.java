package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        assertFalse(testGraph.getAllNodes().isEmpty());
    }

    @Test
    public void getNodeEmptyTest() {
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                assertEquals(testGraph.getNode(new Position(row, col)),
                        NodeType.EMPTY);
            }
        }
    }

    @Test
    public void getNodeOneChangedTest() {
        testGraph.setNode(new Position(0, 1), NodeType.CLOSED);

        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position pos = new Position(row, col);
                if (row == 0 && col == 1) {
                    assertEquals(testGraph.getNode(pos),
                            NodeType.CLOSED);
                } else {
                    assertEquals(testGraph.getNode(pos),
                            NodeType.EMPTY);
                }
            }
        }
    }

    @Test
    public void getNodeTwoChangedTest() {
        testGraph.setNode(new Position(0, 1), NodeType.CLOSED);
        testGraph.setNode(new Position(4, 5), NodeType.OPEN);

        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position pos = new Position(row, col);
                if (row == 0 && col == 1) {
                    assertEquals(testGraph.getNode(pos),
                            NodeType.CLOSED);
                } else if (row == 4 && col == 5) {
                    assertEquals(testGraph.getNode(pos),
                            NodeType.OPEN);
                } else {
                    assertEquals(testGraph.getNode(pos),
                            NodeType.EMPTY);
                }
            }
        }

    }

    @Test
    public void setAllNodesTest() {
        testGraph.setAllNodes(NodeType.WALL);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                assertEquals(NodeType.WALL, testGraph.getNode(new Position(row, col)));
            }
        }
        testGraph.setNode(new Position(3, 4), NodeType.PATH);
        testGraph.setAllNodes(NodeType.EMPTY);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                assertEquals(NodeType.EMPTY, testGraph.getNode(new Position(row, col)));
            }
        }
    }

    @Test
    public void setStartEndTest() {
        Position pos = new Position(4, 3);
        testGraph.setStartPos(pos);
        assertEquals(pos, testGraph.getStartPos());
        Position pos1 = new Position(24, 43);
        testGraph.setEndPos(pos1);
        assertEquals(pos1, testGraph.getEndPos());
    }

}
