package model;

import static org.junit.Assert.assertEquals;

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
    public void setRowTest() {
        assertEquals(testPos.getRow(), 1);
        testPos.setRow(2);
        assertEquals(testPos.getRow(), 2);
    }

    @Test
    public void setColTest() {
        assertEquals(testPos.getCol(), 5);
        testPos.setCol(3);
        assertEquals(testPos.getCol(), 3);
    }
    
    @Test
    public void getDistanceTest() {
        assertEquals(0, Math.round(Position.getDistance(testPos, duplicatePos)));
        assertEquals(14, Math.round(Position.getDistance(testPos, anotherPos)));
        assertEquals(21, Math.round(Position.getDistance(yetAnotherPos, anotherPos)));
    }
}
