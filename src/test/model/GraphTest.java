package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

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
    public void testConstructor() {
        assertEquals(testGraph.getRows(), 5);
        assertEquals(testGraph.getCols(), 6);
        assertEquals(testGraph.getStartPos(), startPos);
        assertEquals(testGraph.getEndPos(), endPos);
        assertFalse(testGraph.getAllNodes().isEmpty());
    }

    @Test
    public void testGetNodeEmpty() {
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                assertEquals(NodeType.EMPTY,
                        testGraph.getNode(new Position(row, col)).getNodeType());
            }
        }
    }

    @Test
    public void testGetNodeOneChanged() {
        testGraph.setNode(new Position(0, 1), new Node(NodeType.CLOSED));

        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position pos = new Position(row, col);
                if (row == 0 && col == 1) {
                    assertEquals(NodeType.CLOSED,
                            testGraph.getNode(pos).getNodeType());
                } else {
                    assertEquals(NodeType.EMPTY,
                            testGraph.getNode(pos).getNodeType());
                }
            }
        }
    }

    @Test
    public void testgetNodeTwoChanged() {
        Node closedNode = new Node(NodeType.CLOSED);
        Node openNode = new Node(NodeType.OPEN);
        testGraph.setNode(new Position(0, 1), closedNode);
        testGraph.setNode(new Position(4, 5), openNode);

        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position pos = new Position(row, col);
                if (row == 0 && col == 1) {
                    assertEquals(closedNode,
                            testGraph.getNode(pos));
                } else if (row == 4 && col == 5) {
                    assertEquals(openNode,
                            testGraph.getNode(pos));
                } else {
                    assertEquals(NodeType.EMPTY,
                            testGraph.getNode(pos).getNodeType());
                }
            }
        }

    }

    @Test
    public void testSetAllNodes() {
        testGraph.setAllNodes(NodeType.WALL);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                assertEquals(NodeType.WALL,
                        testGraph.getNode(new Position(row, col)).getNodeType());
            }
        }
        testGraph.setNode(new Position(3, 4), new Node(NodeType.PATH));
        testGraph.setAllNodes(NodeType.EMPTY);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                assertEquals(NodeType.EMPTY,
                        testGraph.getNode(new Position(row, col)).getNodeType());
            }
        }
    }

    @Test
    public void testSetStartEnd() {
        Position pos = new Position(4, 3);
        testGraph.setStartPos(pos);
        assertEquals(pos, testGraph.getStartPos());
        Position pos1 = new Position(24, 43);
        testGraph.setEndPos(pos1);
        assertEquals(pos1, testGraph.getEndPos());
    }

}
