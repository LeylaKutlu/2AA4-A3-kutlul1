package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.Queue;
import java.util.ArrayDeque;
import org.json.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CurrentState implements DecisionQueue, DroneActions {
    private Decision currentDecision = new Decision();
    private Queue<String> decisions = new ArrayDeque<String>();
    private final Logger logger = LogManager.getLogger();

    public void enqueue(){
        decisions.add(currentDecision.toString());
    }

    public Decision dequeue(Drone drone){
        currentDecision.setDecision(decisions.poll());

        if (Action.HEADING.equals(currentDecision.getAction())){
            drone.setDirection(Direction.fromString(currentDecision.getDirection()));
            logger.info("Headed and facing: " + drone.getDirection().toString());
        }
        return currentDecision;
    }

    public void clearDecisions(){
        decisions.clear();
    }

    public boolean hasPendingDecisions(){
        return !decisions.isEmpty();
    }

    public void fly(){
        currentDecision.setAction(Action.FLY);
    }

    public void echo(Direction direction){
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction.toString());
        currentDecision.setAction(Action.ECHO);
        currentDecision.setParameters(parameters);
    }

    public void heading(Direction direction){
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction.toString());
        currentDecision.setAction(Action.HEADING);
        currentDecision.setParameters(parameters);
    }

    public void stop(){
        currentDecision.setAction(Action.STOP);    }

    public void scan(){
        currentDecision.setAction(Action.SCAN);    }

    public Decision getDecision(){
        return currentDecision;
    }
}