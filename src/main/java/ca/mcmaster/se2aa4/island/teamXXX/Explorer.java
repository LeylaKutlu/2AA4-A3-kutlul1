package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.Direction;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private DecisionMaker decisionMaker;
    private EmergencySite emergencySite;
    private Coordinates coordinates;
    private PreviousState previous;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        int batteryLevel = info.getInt("budget");
        this.drone = new Drone(batteryLevel, Direction.EAST);
        this.decisionMaker = new PhaseDecisionMaker();
        this.coordinates = new Coordinates(1, 1);
        coordinates.attach(drone);
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        Direction prevDirection = drone.getDirection();

        Decision decision = decisionMaker.decideAction(drone, previous);
        logger.info("** Decision: {}", decision);



        if (previous == null) {
            previous = new PreviousState(new Decision(decision), null, prevDirection);
        } else {
            coordinates.update(previous, prevDirection, drone.getDirection());
            previous.setDecision(decision);
            previous.setDirection(prevDirection);
            if (previous.foundSite()) {
                emergencySite = new EmergencySite(drone.getX(), drone.getY());
                logger.info("Emergency site found at: {} {}", drone.getX(), drone.getY());
            }
        }


        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {

        JSONObject responseJSON = new JSONObject(new JSONTokener(new StringReader(s)));
        Response response = new Response(responseJSON);
        logger.info("** Response received:\n"+response.toString());
        Integer cost = response.getCost();
        logger.info("The cost of the action was {}", cost);
        drone.decreaseBattery(cost);
        String status = response.getStatus();
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getExtras();
        logger.info("Additional information received: {}", extraInfo);
        previous.setResponse(response);
        logger.info("Battery level is now {}", drone.getBatteryLevel()); 

        if (Action.STOP.equals(previous.getAction())) {
            deliverFinalReport();
        }
    }

    @Override
    public String deliverFinalReport() {
        String c = emergencySite.getNearestCreek(decisionMaker.getCreeks());
        logger.info("The located creek is: {}", c);
        return "no creek found";
    }

}
