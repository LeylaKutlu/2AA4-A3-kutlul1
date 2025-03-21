package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.Action;
import ca.mcmaster.se2aa4.island.teamXXX.Direction;

import org.json.JSONObject;

public class Decision {
    
    private JSONObject decision;

    public Decision() {
        this.decision = new JSONObject();
    }

    public Action getAction() {
        return Action.valueOf(decision.optString("action", "").toUpperCase());
    }

    public void setAction(Action action) {
        decision.put("action", action.toString());
        decision.remove("parameters");
    }


    public void setParameters(JSONObject parameters) {
        decision.put("parameters", parameters);
    }

    public JSONObject getDecision() {
        return decision;
    }

    public void setDecision(String decision){
        this.decision = new JSONObject(decision);
    }

    @Override
    public String toString() {
        return decision.toString();
    }
}
