package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DecisionMaker {
    private final Logger logger = LogManager.getLogger();

    private Decision decision = new Decision();
    private Decision previousDecision = null;
    private Response previousResponse = null;
    private String direction = "E";
    private JSONObject parameters = new JSONObject();

    // public DecisionMaker() {
    //     this.decision = new Decision();
    //     this.previousDecision = null;
    //     this.previousResponse = null;
    // }

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
        
        if (direction.equals("S") && previousDecision.getAction().equals("heading")) {
            decision.setAction("echo");
            parameters.put("direction", "E");
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (!previousDecision.getAction().equals("echo")) {
            decision.setAction("echo"); 
            if (direction.equals("E")) {
                parameters.put("direction", "E");
            } else if (direction.equals("W")){
                parameters.put("direction", "W");
            }
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if(direction.equals("S") && previousResponse.getRange() == 0){
            decision.setAction("heading");
            parameters.put("direction", "W");
            direction = "W";
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (direction.equals("S") && previousResponse.getRange() > 0){
            decision.setAction("heading");
            parameters.put("direction", "E");
            direction = "E";
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (previousResponse.getRange() == 1 && (direction.equals("E") || direction.equals("W"))) {
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
