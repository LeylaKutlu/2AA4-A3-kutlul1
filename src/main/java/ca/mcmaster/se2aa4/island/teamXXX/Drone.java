package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {
    
    private int batteryLevel;
    private String direction;

    public Drone(int batteryLevel, String direction) {
        this.batteryLevel = batteryLevel;
        this.direction = direction;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void decreaseBattery(int cost) {
        this.batteryLevel -= cost;
    }
}