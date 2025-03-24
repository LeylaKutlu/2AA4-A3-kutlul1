package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.json.JSONTokener;

class ExplorerTest {
    private Explorer explorer;
    private Drone mockDrone;
    private DecisionMaker mockDecisionMaker;
    private PreviousState mockPrevious;
    private Coordinates mockCoordinates;
    private EmergencySite mockEmergencySite;
    private Response mockResponse;

    @BeforeEach
    void setUp() {
        explorer = new Explorer();
        mockDrone = mock(Drone.class);
        mockDecisionMaker = mock(DecisionMaker.class);
        mockPrevious = mock(PreviousState.class);
        mockCoordinates = mock(Coordinates.class);
        mockEmergencySite = mock(EmergencySite.class);
        mockResponse = mock(Response.class);
    }

    @Test
    void testInitializeParsesJsonCorrectly() {
        String jsonInput = "{ \"heading\": \"EAST\", \"budget\": 100 }";

        explorer.initalize(jsonInput);

        assertNotNull(explorer);
    }

    @Test
    void testTakeDecisionDelegatesToDecisionMaker() {
        when(mockDrone.getDirection()).thenReturn(Direction.EAST);
        when(mockDecisionMaker.decideAction(any(), any())).thenReturn(new Decision(Action.FLY));

        String decision = explorer.takeDecision();

        assertEquals("FLY", decision);
        verify(mockDecisionMaker).decideAction(any(), any());
    }

    @Test
    void testAcknowledgeResultsUpdatesBatteryAndStatus() {
        String jsonResponse = "{ \"cost\": 10, \"status\": \"OK\", \"extras\": {} }";
        JSONObject responseJSON = new JSONObject(new JSONTokener(jsonResponse));

        when(mockResponse.getCost()).thenResturn(10);
        when(mockResponse.getStatus()).thenReturn("ok");

        explorer.acknowledgeResults(jsonResponse);

        verify(mockDrone).decreaseBattery(10);
    }

    @Test
    void testDeliverFinalReportReturnsCorrectValue() {
        when(mockEmergencySite.getNearestCreek(any())).thenReturn("Creek123");

        String report = explorer.deliverFinalReport();

        assertEquals("no creek found", report);
    }
}