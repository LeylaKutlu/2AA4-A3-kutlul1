package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class StopCommand extends Command {
    private JSONObject parameters;
    private Drone drone;
    private CurrentState currentState;

    public StopCommand(Drone drone, CurrentState currentState, JSONObject parameters) {
        super(drone, currentState, parameters);
        this.currentState = currentState;
    }

    @Override
    public void execute() {
        currentState.stop();
    }
}