package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class HeadingCommand extends Command {
    private JSONObject parameters;
    private Drone drone;
    private CurrentState currentState;
    private Direction direction;

    public HeadingCommand(Drone drone, CurrentState currentState, JSONObject parameters) {
        super(drone, currentState, parameters);
        this.drone = drone;
        this.currentState = currentState;
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        currentState.heading(drone.getDirection());
    }
}