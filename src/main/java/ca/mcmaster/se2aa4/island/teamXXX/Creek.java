package ca.mcmaster.se2aa4.island.teamXXX;

public class Creek {
    private String creekId;
    private int x;
    private int y;

    public Creek(String creekId, int x, int y) {
        this.creekId = creekId;
        this.x = x;
        this.y = y;
    }

    public String getCreekId() {
        return creekId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}