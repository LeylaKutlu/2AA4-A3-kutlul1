package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhaseDecisionMakerTest {
    private PhaseDecisionMaker decisionMaker;
    private Drone mockDrone;
    private PreviousState mockPrevious;
    private StrategyFactory mockFactory;
    private Strategy mockStrategy;
    private Creeks mockCreeks;
    private CurrentState mockCurrentState;

    @BeforeEach
    void setUp() {
        decisionMaker = new PhaseDecisionMaker();
        mockDrone = mock(Drone.class);
        mockPrevious  = mock(PreviousState.class);
        mockFactory = mock(StrategyFactory.class);
        mockStrategy = mock(Strategy.class);
        mockCreeks = mock(Creeks.class);
        mockCurrentState = mock(CurrentState.class);

        decisionMaker.factory = mockFactory;
        when(mockFactory.getStrategy(anyInt())).thenReturn(mockStrategy);
        when(mockStrategy.decideAction(any(), any(), any(), any(), any()))
            .thenReturn(new Decision(Action.FLY));
    }

    @Test
    void testDecideActionDelegatesToStrategy() {
        Decision decisions = decisionMaker.decideAction(mockDrone, mockPrevious);

        assertNotNull(decision);
        assertEquals(Action.FLY, decision.getAction());
        verify(mockFactory).getStrategy(anyInt());
        verify(mockStrategy).decideAction(any(), any(), any(), any(), any());
    }

    @Test
    void testGetCreeksReturnsSameObject() {
        assertNotNull(decisionMaker.getCreeks());
        assertSame(decisionMaker.getCreeks(), decisionMaker.getCreeks());
    }

    @Test
    void testSetPhaseUpdatesPhaseCorrectly() {
        decisionMaker.setPhase(2);
        Decision decision = decisionMaker.decideAction(mockDrone, mockPrevious);

        verify(mockFactory).getStrategy(2);
    }
}