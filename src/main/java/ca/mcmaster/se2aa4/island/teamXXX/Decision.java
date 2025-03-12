package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Decision {
    
    private JSONObject decision;

    public Decision() {
        this.decision = new JSONObject();
    }

    public String getAction() {
        return decision.optString("action", "");
    }

    public void setAction(String action) {
        decision.put("action", action);
        decision.remove("parameters");
    }


    public void setParameters(JSONObject parameters) {
        decision.put("parameters", parameters);
    }

    public JSONObject getDecision() {
        return decision;
    }

    public String toString() {
        return decision.toString();
    }
}