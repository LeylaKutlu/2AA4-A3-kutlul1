package ca.mcmaster.se2aa4.island.teamXXX;

public class EmergencySite {
    private int x;
    private int y;

    public EmergencySite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getNearestCreek(Creeks creeks) {
        return creeks.getNearestCreek(x, y).getCreekId();
    }

}