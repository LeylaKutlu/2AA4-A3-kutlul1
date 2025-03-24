package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

public class Creeks {

    private List<Creek> creeks;

    public Creeks(){
        this.creeks = new ArrayList<>();
    }

    public Creeks(Creeks other){
        this.creeks = other.creeks;
    }

    public void addCreek(Creek creek) {
        if (creek.getCreekId() != null) {
            creeks.add(creek);
        }
    }


    public Creek getNearestCreek(int x, int y) {
        if (creeks.isEmpty()) {
            return null;
        }
        Creek nearestCreek = creeks.get(0);
        double nearestDistance = Math.sqrt(Math.pow(nearestCreek.getX() - x, 2) + Math.pow(nearestCreek.getY() - y, 2));
        for (Creek creek : creeks) {
            double distance = Math.sqrt(Math.pow(creek.getX() - x, 2) + Math.pow(creek.getY() - y, 2));
            if (distance < nearestDistance) {
                nearestCreek = creek;
                nearestDistance = distance;
            }
        }
        return nearestCreek;
    }
}