package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {
    
    private int batteryLevel;
    private Direction direction;
    private int x = 1;
    private int y = 1; 

    public Drone(int batteryLevel, Direction direction) {
        this.batteryLevel = batteryLevel;
        this.direction = direction;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) { // used?
        this.batteryLevel = batteryLevel;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void decreaseBattery(int cost) {
        this.batteryLevel -= cost;
    }

    public void moveX(int x) {
        this.x += x;
    }

    public void moveY(int y) {
        this.y += y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}