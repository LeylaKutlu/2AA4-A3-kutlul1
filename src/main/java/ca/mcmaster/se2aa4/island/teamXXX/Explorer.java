package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private int batteryLevel;
    private String direction;
    private Response previousResponse = null;
    private JSONObject previousDecision = null;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        this.direction = info.getString("heading");
        this.batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {

        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();
        
        if (batteryLevel < 2) {
            decision.put("action", "stop");
            return decision.toString();
        } 
        
        if (previousDecision == null){
            decision.put("action", "fly");
        } else if (direction.equals("S") && previousDecision.getString("action").equals("heading")) {
            decision.put("action", "echo");
            parameters.put("direction", "E");
            decision.put("parameters", parameters);
        } else if (!previousDecision.getString("action").equals("echo")) {
            decision.put("action", "echo"); 
            if (direction.equals("E")) {
                parameters.put("direction", "E");
            } else if (direction.equals("W")){
                parameters.put("direction", "W");
            }
            decision.put("parameters", parameters);
        } else if(direction.equals("S") && previousResponse.getRange() == 0){
            decision.put("action", "heading");
            parameters.put("direction", "W");
            direction = "W";
            decision.put("parameters", parameters);
        } else if (direction.equals("S") && previousResponse.getRange() > 0){
            decision.put("action", "heading");
            parameters.put("direction", "E");
            direction = "E";
            decision.put("parameters", parameters); 
        } else if (previousResponse.getRange() == 1 && (direction.equals("E") || direction.equals("W"))) {
            decision.put("action", "heading"); 
            parameters.put("direction", "S"); 
            direction = "S";
            decision.put("parameters", parameters);
        } else if (previousResponse.groundFound()) {
            decision.put("action", "stop");
        } else if (!previousResponse.groundFound()) {
            decision.put("action", "fly");
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
        batteryLevel -= cost;
        String status = response.getStatus();
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getExtras();
        logger.info("Additional information received: {}", extraInfo);
        previousResponse = response;
        logger.info("Battery level is now {}", batteryLevel);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
