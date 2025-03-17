package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

public class Creeks {

    private List<Creek> creeks;

    public Creeks() {
        this.creeks = new ArrayList<>();
    }

    public void addCreek(Creek creek) {
        creeks.add(creek);
    }

    public List<Creek> getCreeks() {
        return new ArrayList<>(creeks);
    }
}