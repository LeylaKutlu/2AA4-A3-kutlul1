package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhaseDecisionMakerTest {
    private PhaseDecisionMaker decisionMaker;
    private Drone Drone;
    private PreviousState previous;
    private StrategyFactory factory;
    private Strategy strategy;
    private Creeks creeks;
    private CurrentState currentState;

    @BeforeEach
    void setUp() {
        decisionMaker = new PhaseDecisionMaker();
        factory = new StrategyFactory();
        decisionMaker.factory = factory;
        creeks = new Creeks();
        }
    }

    @Test
    void testDecideAction() {
        decisionMaker.setPhase(1);
        Decision decision = decisionMaker.decideAction(drone, previous);
        
        assertNotNull(decision);
    }

    @Test
    void testGetCreeks() {
        assertNotNull(decisionMaker.getCreeks());
        assertSame(decisionMaker.getCreeks(), decisionMaker.getCreeks());
    }

    @Test
    void testSetPhase() {
        decisionMaker.setPhase(2);
        assertEquals(2, decisionMaker.getPhase());
    }
}