package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class CommandFactory {
    private JSONObject parameters;
    private Drone drone;
    private CurrentState currentState;
    private Action action;
    private Command coommand;

    public Command getCommand(Action action, Drone drone, CurrentState currentState, JSONObject parameters){
        if (action == null) {
            return null;
        }
        if (action == Action.FLY) {
            return new FlyCommand(drone, currentState, parameters);
        }
        if (action == Action.STOP) {
            return new StopCommand(drone, currentState, parameters);
        }
        if (action == Action.ECHO) {
            return new EchoCommand(drone, currentState, parameters);
        }
        if (action == Action.SCAN) {
            return new ScanCommand(drone, currentState, parameters);
        }
        if (action == Action.HEADING) {
            return new HeadingCommand(drone, currentState, parameters);
        }

        return null;
    }
}