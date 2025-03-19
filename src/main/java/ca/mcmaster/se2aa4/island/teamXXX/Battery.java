package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Battery {
    private boolean lowBattery(Drone drone, Decision decision) {
        if (drone.getBatteryLevel() < 2) {
            decision.setAction(Action.STOP);
            return true;
        }
        return false;
    }
}