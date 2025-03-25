package ca.mcmaster.se2aa4.island.teamXXX;

public class EmergencySite {
    private int x;
    private int y;
    private String siteId;

    public EmergencySite(String siteId, int x, int y) {
        this.x = x;
        this.y = y;
        this.siteId = siteId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getNearestCreek(Creeks creeks) {
        return creeks.getNearestCreek(x, y).getCreekId();
    }

}