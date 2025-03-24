package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone implements Observer{
    
    private int batteryLevel;
    private Direction direction = Direction.EAST;
    private int x = 1;
    private int y = 1; 

    public Drone(int batteryLevel, Direction direction) {
        this.batteryLevel = batteryLevel;
        this.direction = direction;
    }

    public int getBatteryLevel() {
        return batteryLevel;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override 
    public void updateCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }



}