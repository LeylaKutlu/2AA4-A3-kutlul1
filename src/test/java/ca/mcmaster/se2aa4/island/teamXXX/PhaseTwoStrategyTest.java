package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class PhaseTwoStrategyTest { 
    private Decision decision;
    private Drone drone;
    private Creeks creeks;
    private PhaseDecisionMaker phaseDecisionMaker;
    private CurrentState currentState;
    private PhaseTwoStrategy phaseTwoStrategy;
    private PreviousState previousState;
    private Response response;


    @BeforeEach
    public void setUp() {
        drone = new Drone(100, Direction.EAST);
        currentState = new CurrentState();
        creeks = new Creeks();
        phaseDecisionMaker = new PhaseDecisionMaker();
        phaseTwoStrategy = new PhaseTwoStrategy();
    }

    @Test
    public void echoAndGroundNotFoundTest() {
        Decision decision = new Decision();
        decision.setAction(Action.ECHO);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", "N");
        decision.setParameters(parameters);

        JSONObject responseJSON = new JSONObject();
        responseJSON.put("status", "OK");
        responseJSON.put("found", "WATER");
        responseJSON.put("range", 1);

        Response response = new Response(responseJSON);
        Direction direction = Direction.NORTH;
        PreviousState previousState = new PreviousState(decision, response, direction);

        Decision decisionStrategy = phaseTwoStrategy.decideAction(drone, phaseDecisionMaker, creeks, previousState, currentState);
        assertNotNull(decisionStrategy);
        assertEquals(Action.FLY, decisionStrategy.getAction());
    } 

    @Test
    public void echoDefaultTest() {
        Decision decision = new Decision();
        decision.setAction(Action.FLY);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", "N");
        decision.setParameters(parameters);

        JSONObject responseJSON = new JSONObject();
        responseJSON.put("status", "OK");
        responseJSON.put("found", "WATER");
        responseJSON.put("range", 5);

        Response response = new Response(responseJSON);
        Direction direction = Direction.NORTH;
        PreviousState previousState = new PreviousState(decision, response, direction);

        Decision decisionStrategy = phaseTwoStrategy.decideAction(drone, phaseDecisionMaker, creeks, previousState, currentState);
        assertNotNull(decisionStrategy);
        assertEquals(Action.ECHO, decisionStrategy.getAction());
    } 

}