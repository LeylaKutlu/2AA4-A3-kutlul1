package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
// class may not be needed 
public class ActionHandler {
    public void fly(Decision decision) {
        decision.setAction(Action.FLY);
    }

    public void echo(Decision decision, Direction direction) {
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction.toString());
        decision.setAction(Action.ECHO);
        decision.setParameters(parameters);
    }

    public void heading(Decision decision, Direction direction) {
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction.toString());
        decision.setAction(Action.HEADING);
        decision.setParameters(parameters);
    }

    public void stop(Decision decision) {
        decision.setAction(Action.STOP);
    }

    public void scan(Decision decision) {
        decision.setAction(Action.SCAN);
    }
}