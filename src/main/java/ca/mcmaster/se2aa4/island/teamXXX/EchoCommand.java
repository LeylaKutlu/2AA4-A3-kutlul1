package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class EchoCommand extends Command {
    private JSONObject parameters;
    private Drone drone;
    private CurrentState currentState;
    private Direction direction;

    public EchoCommand(Drone drone, CurrentState currentState, JSONObject parameters) {
        super(drone, currentState, parameters);
        this.drone = drone;
        this.currentState = currentState;
        this.parameters = parameters;
        this.direction = direction;
    }

    @Override
    public void execute() {
        currentState.echo(drone.getDirection());
    }
}