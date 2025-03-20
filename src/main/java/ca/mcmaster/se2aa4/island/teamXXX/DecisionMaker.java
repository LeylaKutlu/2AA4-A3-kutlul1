package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.Action;
import ca.mcmaster.se2aa4.island.teamXXX.Direction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

public abstract class DecisionMaker {
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

    public abstract String decideAction(Drone drone);
}