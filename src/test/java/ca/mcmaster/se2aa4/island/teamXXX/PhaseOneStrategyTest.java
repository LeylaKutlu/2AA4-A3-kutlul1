package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class PhaseOneStrategyTest { 
    private Decision decision;
    private Drone drone;
    private Creeks creeks;
    private PhaseDecisionMaker phaseDecisionMaker;
    private CurrentState currentState;
    private PhaseOneStrategy phaseOneStrategy;
    private PreviousState previousState;
    private Response response;


    @BeforeEach
    public void setUp() {
        drone = new Drone(100, Direction.EAST);
        currentState = new CurrentState();
        creeks = new Creeks();
        phaseDecisionMaker = new PhaseDecisionMaker();
        phaseOneStrategy = new PhaseOneStrategy();
    }

    @Test
    public void initialEchoTest() {
        Decision decision = phaseOneStrategy.decideAction(drone, phaseDecisionMaker, creeks, null, currentState);
        assertNotNull(decision);
        assertEquals(Action.ECHO, decision.getAction());
        assertEquals(Direction.EAST, drone.getDirection());
    } 

    @Test
    public void groundScanTest() {
        Decision decision = new Decision();
        decision.setAction(Action.ECHO);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", "N");
        decision.setParameters(parameters);

        JSONObject responseJSON = new JSONObject();
        responseJSON.put("status", "OK");
        responseJSON.put("found", "GROUND");
        responseJSON.put("range", 0);

        Response response = new Response(responseJSON);
        Direction direction = Direction.NORTH;
        PreviousState previousState = new PreviousState(decision, response, direction);

        Decision decisionStrategy = phaseOneStrategy.decideAction(drone, phaseDecisionMaker, creeks, previousState, currentState);
        assertNotNull(decisionStrategy);
        assertEquals(Action.FLY, decisionStrategy.getAction());
        assertEquals(Direction.WEST, drone.getDirection());
    } 
}