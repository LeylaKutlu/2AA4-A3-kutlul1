package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DroneTest {

    private Drone drone;

    @BeforeEach
    public void setUp() {
        drone = new Drone(100, Direction.EAST);
    }

    @Test
    public void constructorTest() {
        assertEquals(100, drone.getBatteryLevel());
        assertEquals(Direction.EAST, drone.getDirection());
        assertEquals(1, drone.getX());
        assertEquals(1, drone.getX());
    }

    @Test
    public void getDirectionTest() {
        assertEquals(Direction.EAST, drone.getDirection());
    }

    @Test
    public void setDirectionTest() {
        drone.setDirection(Direction.NORTH);
        assertEquals(Direction.NORTH, drone.getDirection());
    }

    @Test
    public void decreaseBatteryTest() {
        drone.decreaseBattery(10);
        assertEquals(90, drone.getBatteryLevel());
        drone.decreaseBattery(30);
        assertEquals(60, drone.getBatteryLevel());
        drone.decreaseBattery(0);
        assertEquals(60, drone.getBatteryLevel());
    }

    @Test
    public void getXTest() {
        assertEquals(1, drone.getX());
    }

    @Test
    public void getYTest() {
        assertEquals(1, drone.getY());
    }

    @Test
    public void updateCoordinatesTest() {
        drone.updateCoordinates(5, 10);
        assertEquals(5, drone.getX());
        assertEquals(10, drone.getY());
    }

}