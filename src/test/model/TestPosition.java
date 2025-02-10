package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPosition {

    private Position testPos;
    private Position anotherPos;
    private Position yetAnotherPos;
    private Position duplicatePos;

    @BeforeEach
    public void runBefore() {
        testPos = new Position(1, 5);
        anotherPos = new Position(0, 19);
        yetAnotherPos = new Position(20, 12);
        duplicatePos = new Position(1, 5);
    }

    @Test
    public void constructorTest() {
        assertEquals(testPos.getRow(), 1);
        assertEquals(testPos.getCol(), 5);
    }

    @Test
    public void equalsTest() {
        assertTrue(new Position(53, 10).equals(new Position(53, 10)));
        Position pos = new Position(1, 4);
        assertTrue(pos.equals(pos));
        assertFalse(pos.equals(new Position(1, 1)));
        assertFalse(pos.equals(new Position(2, 4)));
        assertFalse(pos.equals(null));
        assertFalse(pos.equals(new ArrayList<>()));
    }

    @Test
    public void setRowColTest() {
        testPos.setCol(3);
        assertEquals(1, testPos.getRow());
        assertEquals(3, testPos.getCol());
        testPos.setRow(-2);
        assertEquals(-2, testPos.getRow());
        assertEquals(3, testPos.getCol());
    }

    @Test
    public void getDistanceTest() {
        assertEquals(0, Math.round(Position.getDistance(testPos, duplicatePos)));
        assertEquals(14, Math.round(Position.getDistance(testPos, anotherPos)));
        assertEquals(21, Math.round(Position.getDistance(yetAnotherPos, anotherPos)));
    }
}
