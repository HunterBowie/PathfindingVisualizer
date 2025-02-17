package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class NodeTest {

    private Node node;
    private Node anotherNode;

    @BeforeEach
    public void runBefore() {
        node = new Node(NodeType.EMPTY);
        anotherNode = new Node(NodeType.OPEN);
    }

    @Test
    public void testConstructor() {
        assertEquals(Math.round(Double.POSITIVE_INFINITY), Math.round(node.getFCost()));
        assertEquals(Math.round(Double.POSITIVE_INFINITY), Math.round(node.getGCost()));
        assertEquals(-1, Math.round(node.getHCost()));
        assertEquals(NodeType.EMPTY, node.getNodeType());
    }

    @Test
    public void testIsLowerCost() {
        node.setGCost(4);
        node.setHCost(1);
        anotherNode.setGCost(3);
        anotherNode.setHCost(2);

        assertFalse(node.isLowerCost(anotherNode));
        assertFalse(anotherNode.isLowerCost(node));
        node.setGCost(0);
        assertFalse(node.isLowerCost(anotherNode));
        assertTrue(anotherNode.isLowerCost(node));
        node.setHCost(10);
        assertTrue(node.isLowerCost(anotherNode));
        assertFalse(anotherNode.isLowerCost(node));
    }

    @Test
    public void testToString() {
        Node newNode = new Node(NodeType.EMPTY);
        assertEquals("-", newNode.toString());
        Node newNode1 = new Node(NodeType.CLOSED);
        assertEquals("0", newNode1.toString());
        Node newNode2 = new Node(NodeType.PATH);
        assertEquals("P", newNode2.toString());
        Node newNode3 = new Node(NodeType.OPEN);
        assertEquals("O", newNode3.toString());
        Node newNode4 = new Node(NodeType.WALL);
        assertEquals("X", newNode4.toString());
    }

    @Test
    public void testGetFCost() {
        node.setGCost(1);
        node.setHCost(3);
        assertEquals(4, Math.round(node.getFCost()));
    }


    
}
