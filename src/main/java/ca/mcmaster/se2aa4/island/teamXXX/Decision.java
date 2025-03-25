package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Decision {
    
    private JSONObject decision;
    private JSONObject parameters;

    public Decision() {
        this.decision = new JSONObject();
    }

    public Decision(Decision decision) {
        this.decision = decision.decisionJSON();
    }

    public Action getAction() {
        return Action.valueOf(decision.optString("action", "").toUpperCase());
    }

    public void setAction(Action action) {
        decision.put("action", action.toString());
        decision.remove("parameters");
    }

    public void setParameters(JSONObject parameters) {
        this.parameters = parameters;
        decision.put("parameters", parameters);
    }

    private JSONObject decisionJSON() {
        return decision;
    }

    public void setDecision(String decision){
        this.decision = new JSONObject(decision);
    }

    public String getDirection(){
        return parameters.optString("direction", "");
    }

    @Override
    public String toString() {
        return decision.toString();
    }
}
