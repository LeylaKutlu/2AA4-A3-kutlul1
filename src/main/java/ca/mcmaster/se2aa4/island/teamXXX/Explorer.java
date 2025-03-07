package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private String direction;
    private Response previousResponse = null;
    private JSONObject previousDecision = null;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        this.direction = info.getString("heading");
        int batteryLevel = info.getInt("budget");
        this.drone = new Drone(batteryLevel, direction);
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {

        Decision decision = new JSONObject();
        JSONObject parameters = new JSONObject();
        
        if (drone.getBatteryLevel() < 2) {
            decision.setAction("stop");
            return decision.toString();
        } 
        
        if (previousDecision == null){
            decision.setAction("fly");
        } else if (direction.equals("S") && previousDecision.getAction.equals("heading")) {
            decision.setAction("echo");
            parameters.put("direction", "E");
            decision.setParameters(parameters);
        } else if (!previousDecision.getAction.equals("echo")) {
            decision.setAction("echo"); 
            if (direction.equals("E")) {
                parameters.put("direction", "E");
            } else if (direction.equals("W")){
                parameters.put("direction", "W");
            }
            decision.setParameters(parameters);
        } else if(direction.equals("S") && previousResponse.getRange() == 0){
            decision.setAction("heading");
            parameters.put("direction", "W");
            direction = "W";
            decision.setParameters(parameters);
        } else if (direction.equals("S") && previousResponse.getRange() > 0){
            decision.setAction("heading");
            parameters.put("direction", "E");
            direction = "E";
            decision.setParameters(parameters);
        } else if (previousResponse.getRange() == 1 && (direction.equals("E") || direction.equals("W"))) {
            decision.setAction("heading"); 
            parameters.put("direction", "S"); 
            direction = "S";
            decision.setParameters(parameters);
        } else if (previousResponse.groundFound()) {
            decision.setAction("stop");
        } else if (!previousResponse.groundFound()) {
            decision.setAction("fly");
        }

        logger.info("** Decision: {}",decision.toString(4));
        previousDecision = decision;

        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject JSONresponse = new JSONObject(new JSONTokener(new StringReader(s)));
        Response response = new Response(JSONresponse);
        logger.info("** Response received:\n"+response.getResponse());
        Integer cost = response.getCost();
        logger.info("The cost of the action was {}", cost);
        drone.decreaseBattery(cost);
        String status = response.getStatus();
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getExtras();
        logger.info("Additional information received: {}", extraInfo);
        previousResponse = response;
        logger.info("Battery level is now {}", drone.getBatteryLevel());
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
