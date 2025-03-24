package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class EmergencySiteTest {
    private EmergencySite emergencySite;
    private Creeks creeks;

    @BeforeEach
    public void setUp() {
        EmergencySite site = new EmergencySite(3, 5);
        creeks = new Creeks();
        creeks.addCreek(new Creek("creek1", 1, 1));
        creeks.addCreek(new Creek("creek2", 5, 3));
    }

    @Test
    public void getNearestCreekTest() {
        emergencySite = new EmergencySite(2, 2);
        assertEquals("creek1", emergencySite.getNearestCreek(creeks));
    }
}