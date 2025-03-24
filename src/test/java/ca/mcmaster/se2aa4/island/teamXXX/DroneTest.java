package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DroneTest {

    @Test
    public void testDroneConstructor() {

        Drone done = new Drone(100, Direction.NORTH);

        assertEquals(100, drone.getBatteryLevel());
        assertEquals(Direction.NORTH, drone.getDirection());

    }

    @Test
    public void testSetDirection() {

        Drone drone = new Drone(100, Direction.NORTH);
        drone.setDirection(Direction.WEST);

        assertEquals(Direction.WEST, drone.getDirection());

    }

    @Test
    public void testDecreaseBattery() {

        Drone drone = new Drone(100, Direction.EAST);
        drone.decreaseBattery(10);

        assertEquals(90, drone.getBatteryLevel());

    }

    @Test
    public void testSetCoordinates() {

        Drone drone = new Drone(100, Direction.SOUTH);
        drone.setCoordinates(5, 10);

        assertEquals(5, drone.x);
        assertEquals(10, drone.y);

    }

    @Test
    public void testDefaultDirection() {

        Drone drone = new Drone(100, Direction.NORTH);
        Drone defaultDrone = new Drone(100, Direction.EAST);

        assertEquals(Direction.EAST, defaultDrone.getDirection());

    }

}