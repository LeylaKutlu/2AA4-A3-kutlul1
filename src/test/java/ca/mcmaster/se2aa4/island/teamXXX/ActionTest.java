package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ActionTest {

    @Test
    public void actionTest() {
        assertEquals("fly", Action.FLY.toString());
        assertEquals("stop", Action.STOP.toString());
        assertEquals("echo", Action.ECHO.toString());
        assertEquals("scan", Action.SCAN.toString());
        assertEquals("heading", Action.HEADING.toString());
    }
}