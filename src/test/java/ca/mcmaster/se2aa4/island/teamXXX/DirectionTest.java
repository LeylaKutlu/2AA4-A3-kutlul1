package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DirectionTest {

    @Test
    public void northTest() {
        assertEquals("N", Direction.NORTH.toString());
        assertEquals(Direction.NORTH, Direction.valueOf("NORTH"));
    }

    @Test
    public void eastTest() {
        assertEquals("E", Direction.EAST.toString());
        assertEquals(Direction.EAST, Direction.valueOf("EAST"));
    }

    @Test
    public void westTest() {
        assertEquals("W", Direction.WEST.toString());
        assertEquals(Direction.WEST, Direction.valueOf("WEST"));
    }

    @Test
    public void southTest() {
        assertEquals("S", Direction.SOUTH.toString());
        assertEquals(Direction.SOUTH, Direction.valueOf("SOUTH"));
    }

    @Test
    public void invalidStringTest() {
        assertThrows(IllegalArgumentException.class, () -> Direction.valueOf("INVALID"));
    }
}