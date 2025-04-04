package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class DecisionTest { 
    private Decision decision;
    private Drone drone;

    @BeforeEach
    public void setUp() {
        decision = new Decision();
        drone = new Drone(0, Direction.NORTH);
    }

    @Test
    public void setActionTest() {
        decision.setAction(Action.FLY);
        assertEquals(Action.FLY, decision.getAction());
    }

    @Test
    public void setParametersTest() {
        JSONObject parameters = new JSONObject();
        parameters.put("direction", "E");
        decision.setParameters(parameters);
        assertEquals("E", decision.getDirection());
    }

    @Test
    public void toStringTest() {
        decision.setAction(Action.STOP);
        assertEquals("{\"action\":\"stop\"}", decision.toString());
    }
}