package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DecisionMaker {
    private final Logger logger = LogManager.getLogger();

    private JSONObject decision;
    private JSONObject previousDecision;
    private Response previousResponse;
    private String direction;

    public DecisionMaker() {
        this.decision = new JSONObject();
        this.previousDecision = null;
        this.previousResponse = null;
    }

    public void setResponse(Response previousResponse) {
        this.previousResponse = previousResponse;
    }

    public void setDecision(JSONObject previousDecision) {
        this.previousDecision = previousDecision;
    }

    public JSONObject getDecision() {
        return decision;
    }

    public String decideAction(Drone drone) {
        if (direction == null){
            direction = "E"; //what is the default can be changed accordingly
        }

        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction);
        JSONObject decision = new JSONObject();
        
        if (drone.getBatteryLevel() < 2) {
            decision.put("action", "stop");
            return decision.toString();
        } 
        
        if (previousDecision == null){
            decision.put("action", "fly");
            return decision.toString();
        } 
        
        if (direction.equals("S") && previousDecision.getString("action").equals("heading")) {
            decision.put("action", "echo");
            parameters.put("direction", "E");
            decision.put("parameters", parameters);
            return decision.toString();
        } 
        
        if (!previousDecision.getString("action").equals("echo")) {
            decision.put("action", "echo"); 
            if (direction.equals("E")) {
                parameters.put("direction", "E");
            } else if (direction.equals("W")){
                parameters.put("direction", "W");
            }
            decision.put("parameters", parameters);
            return decision.toString();
        } 
        
        if(direction.equals("S") && previousResponse.getRange() == 0){
            decision.put("action", "heading");
            parameters.put("direction", "W");
            direction = "W";
            decision.put("parameters", parameters);
            return decision.toString();
        } 
        
        if (direction.equals("S") && previousResponse.getRange() > 0){
            decision.put("action", "heading");
            parameters.put("direction", "E");
            direction = "E";
            decision.put("parameters", parameters);
            return decision.toString();
        } 
        
        if (previousResponse.getRange() == 1 && (direction.equals("E") || direction.equals("W"))) {
            decision.put("action", "heading");
            parameters.put("direction", "S"); 
            direction = "S";
            decision.put("parameters", parameters);
            return decision.toString();
        } 
        
        if (previousResponse.groundFound()) {
            decision.put("action", "stop");
            logger.info("ground"); //remove
            return decision.toString();
        } 
        
        if (!previousResponse.groundFound()) {
            decision.put("action", "fly");
            return decision.toString();
        }

        return decision.toString();
    }
}
