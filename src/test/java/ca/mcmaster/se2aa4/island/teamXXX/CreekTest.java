package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class CreekTest {
    private Creek creek;

    @BeforeEach
    public void setUp() {
        creek = new Creek("creek1", 10, 20);
    }

    @Test
    public void getCreekTest() {
        assertEquals("creek1", creek.getCreekId());
    }

    @Test
    public void getXTest() {
        assertEquals(10, creek.getX());
    }

    @Test
    public void getYTest() {
        assertEquals(20, creek.getY());
    }

}