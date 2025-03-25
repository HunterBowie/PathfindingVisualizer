package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
    public void testResetAllNodes() {
        testGraph.resetAllNodes();
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Node node = testGraph.getNode(new Position(row, col));
                assertEquals(NodeType.EMPTY, node.getNodeType());
                assertTrue(Double.POSITIVE_INFINITY == node.getGCost());
                assertTrue(-1 == node.getHCost());
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

    @Test
    public void testIsLegalPosition() {
        assertTrue(testGraph.isLegalPosition(new Position(1, 1)));
        assertFalse(testGraph.isLegalPosition(new Position(-1, 1)));
        assertFalse(testGraph.isLegalPosition(new Position(1, -1)));
        assertFalse(testGraph.isLegalPosition(new Position(5, 0)));
        assertFalse(testGraph.isLegalPosition(new Position(0, 6)));
    }

    @Test
    public void testSoftResetAllNodes() {
        testGraph.getNode(endPos).setNodeType(NodeType.WALL);
        testGraph.softResetAllNodes();

        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position pos = new Position(row, col);
                Node node = testGraph.getNode(pos);
                if (pos.equals(endPos)) {
                    assertEquals(NodeType.WALL, node.getNodeType());
                } else {
                    assertEquals(NodeType.EMPTY, node.getNodeType());
                }
                assertTrue(Double.POSITIVE_INFINITY == node.getGCost());
                assertTrue(-1 == node.getHCost());
            }
        }
    }

    @Test
    public void testAddWall() {
        Position pos = new Position(1, 3);
        testGraph.addWall(pos);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position currentPos = new Position(row, col);
                NodeType type = testGraph.getNode(currentPos).getNodeType();
                if (currentPos.equals(pos)) {
                    assertEquals(NodeType.WALL, type);
                } else {
                    assertEquals(NodeType.EMPTY, type);
                }
               
            }
        }
        testGraph.addWall(pos);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position currentPos = new Position(row, col);
                NodeType type = testGraph.getNode(currentPos).getNodeType();
                if (currentPos.equals(pos)) {
                    assertEquals(NodeType.WALL, type);
                } else {
                    assertEquals(NodeType.EMPTY, type);
                }
               
            }
        }


    }
    @Test
    public void testRemoveWall() {
        Position pos = new Position(1, 3);
        testGraph.addWall(pos);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position currentPos = new Position(row, col);
                NodeType type = testGraph.getNode(currentPos).getNodeType();
                if (currentPos.equals(pos)) {
                    assertEquals(NodeType.WALL, type);
                } else {
                    assertEquals(NodeType.EMPTY, type);
                }
               
            }
        }
        testGraph.removeWall(pos);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position currentPos = new Position(row, col);
                NodeType type = testGraph.getNode(currentPos).getNodeType();
                assertEquals(NodeType.EMPTY, type);
                
               
            }
        }
        testGraph.removeWall(pos);
        for (int row = 0; row < testGraph.getRows(); row++) {
            for (int col = 0; col < testGraph.getCols(); col++) {
                Position currentPos = new Position(row, col);
                NodeType type = testGraph.getNode(currentPos).getNodeType();
                assertEquals(NodeType.EMPTY, type);
                
               
            }
        }

    }

}
