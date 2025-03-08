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
    private DecisionMaker decisionMaker;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        this.direction = info.getString("heading");
        int batteryLevel = info.getInt("budget");
        this.drone = new Drone(batteryLevel, direction);
        this.decisionMaker = new DecisionMaker();
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {

        if (previousResponse != null) {
            decisionMaker.setResponse(previousResponse);
        }
        if (previousDecision != null) {
            decisionMaker.setDecision(previousDecision);
        }

        String decision = decisionMaker.decideAction(drone);
        logger.info("** Decision: {}", decision);
        previousDecision = new JSONObject(decision);
        return decision;
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
