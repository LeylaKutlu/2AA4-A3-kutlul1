package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public abstract class Command {
    private JSONObject parameters;
    private Drone drone;
    private CurrentState currentState;

    public Command(Drone drone, CurrentState currentState, JSONObject parameters) {
        this.drone = drone;
        this.currentState = currentState;
        this.parameters = parameters;
    }

    public abstract void execute();
}