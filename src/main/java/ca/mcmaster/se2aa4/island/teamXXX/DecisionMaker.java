package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.Action;
import ca.mcmaster.se2aa4.island.teamXXX.Direction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

public class DecisionMaker {
    protected final Logger logger = LogManager.getLogger();

    protected Decision decision = new Decision();
    protected Decision previousDecision = null;
    protected Response previousResponse = null;
    protected Direction direction = Direction.EAST;
    protected JSONObject parameters = new JSONObject();

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
            decision.setAction(Action.STOP);
            return decision.toString();
        } 
        
        if (previousDecision == null){
            decision.setAction(Action.FLY);
            return decision.toString();
        } 
        
        if (Direction.SOUTH.equals(direction) && Action.HEADING.equals(previousDecision.getAction())) {
            decision.setAction(Action.ECHO);
            parameters.put("direction", Direction.EAST.toString());
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (!Action.ECHO.equals(previousDecision.getAction())) {
            decision.setAction(Action.ECHO); 
            if (Direction.EAST.equals(direction)) {
                parameters.put("direction", Direction.EAST.toString());
            } else if (Direction.WEST.equals(direction)){
                parameters.put("direction", Direction.WEST.toString());
            }
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if(Direction.SOUTH.equals(direction) && previousResponse.getRange() == 0){
            decision.setAction(Action.HEADING);
            parameters.put("direction", Direction.WEST.toString());
            direction = Direction.WEST;
            drone.setDirection("W");
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (Direction.SOUTH.equals(direction) && previousResponse.getRange() > 0){
            decision.setAction(Action.HEADING);
            parameters.put("direction", Direction.EAST.toString());
            direction = Direction.EAST;
            drone.setDirection("E");
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (previousResponse.getRange() == 1 && (Direction.EAST.equals(direction) || Direction.WEST.equals(direction))) {
            decision.setAction(Action.HEADING);
            parameters.put("direction", Direction.SOUTH.toString()); 
            direction = Direction.SOUTH;
            drone.setDirection("S");
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (previousResponse.groundFound()) {
            decision.setAction(Action.STOP);
            return decision.toString();
        } 
        
        if (!previousResponse.groundFound()) {
            decision.setAction(Action.FLY);
            return decision.toString();
        }

        return decision.toString();
    }
}
