package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPosition {

    Position testPos;

    @BeforeEach
    void runBefore() {
        testPos = new Position(1, 5);
    }

    @Test
    void constructorTest() {
        assertEquals(testPos.getRow(), 1);
        assertEquals(testPos.getCol(), 5);
    }

    @Test
    void setRowTest() {
        assertEquals(testPos.getRow(), 1);
        testPos.setRow(2);
        assertEquals(testPos.getRow(), 2);
    }

    @Test
    void setColTest() {
        assertEquals(testPos.getCol(), 5);
        testPos.setCol(3);
        assertEquals(testPos.getCol(), 3);
    }
}
