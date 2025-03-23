package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class ActionHandler {
    public static void fly(Decision decision) {
        decision.setAction(Action.FLY);
    }

    public static void echo(Decision decision, Direction direction) {
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction.toString());
        decision.setAction(Action.ECHO);
        decision.setParameters(parameters);
    }

    public static void heading(Decision decision, Direction direction) {
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction.toString());
        decision.setAction(Action.HEADING);
        decision.setParameters(parameters);
    }

    public static void stop(Decision decision) {
        decision.setAction(Action.STOP);
    }

    public static void scan(Decision decision) {
        decision.setAction(Action.SCAN);
    }
}