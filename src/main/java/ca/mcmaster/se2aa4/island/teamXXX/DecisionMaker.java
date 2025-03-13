package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

public class DecisionMaker {
    private final Logger logger = LogManager.getLogger();

    private Decision decision = new Decision();
    private Decision previousDecision = null;
    private Response previousResponse = null;
    private String direction = "E";
    private JSONObject parameters = new JSONObject();

    public void setResponse(Response previousResponse) {
        this.previousResponse = previousResponse;
    }

    public void setDecision(Decision previousDecision) {
        this.previousDecision = previousDecision;
    }

    public Decision getDecision() {
        return decision;
    }

    public String decideAction(Drone drone) {
        
        if (drone.getBatteryLevel() < 2) {
            decision.setAction("stop");
            return decision.toString();
        } 
        
        if (previousDecision == null){
            decision.setAction("fly");
            return decision.toString();
        } 
        
        if ("S".equals(direction) && "heading".equals(previousDecision.getAction())) {
            decision.setAction("echo");
            parameters.put("direction", "E");
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (!"echo".equals(previousDecision.getAction())) {
            decision.setAction("echo"); 
            if ("E".equals(direction)) {
                parameters.put("direction", "E");
            } else if ("W".equals(direction)){
                parameters.put("direction", "W");
            }
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if("S".equals(direction) && previousResponse.getRange() == 0){
            decision.setAction("heading");
            parameters.put("direction", "W");
            direction = "W";
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if ("S".equals(direction) && previousResponse.getRange() > 0){
            decision.setAction("heading");
            parameters.put("direction", "E");
            direction = "E";
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (previousResponse.getRange() == 1 && ("E".equals(direction) || "W".equals(direction))) {
            decision.setAction("heading");
            parameters.put("direction", "S"); 
            direction = "S";
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (previousResponse.groundFound()) {
            decision.setAction("stop");
            logger.info("ground"); //remove
            return decision.toString();
        } 
        
        if (!previousResponse.groundFound()) {
            decision.setAction("fly");
            return decision.toString();
        }

        return decision.toString();
    }
}
