package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class CurrentStateTest {
    private CurrentState currentState;
    private Decision decision;

    @BeforeEach
    public void setUp() {
        currentState = new CurrentState();
        decision = new Decision();
    }

    @Test
    public void enqueueTest() {
        currentState.fly();
        currentState.enqueue();
        assertTrue(currentState.hasPendingDecisions());
        assertEquals(currentState.dequeue().toString(), "{\"action\":\"fly\"}");
    }

    @Test
    public void clearQueueTest() {
        currentState.fly();
        currentState.enqueue();
        currentState.clearDecisions();
        assertFalse(currentState.hasPendingDecisions());
    }   

    @Test
    public void notEmptyQueueTest() {
        assertFalse(currentState.hasPendingDecisions());
        currentState.fly();
        currentState.enqueue();
        assertTrue(currentState.hasPendingDecisions());
    } 

    @Test
    public void flyTest() {
        currentState.fly();
        assertEquals(currentState.getDecision().getAction(), Action.FLY);
    }

    @Test
    public void echoTest() {
        currentState.echo(Direction.SOUTH);
        assertEquals(currentState.getDecision().getAction(), Action.ECHO);
    }

    @Test
    public void scanTest() {
        currentState.scan();
        assertEquals(currentState.getDecision().getAction(), Action.SCAN);
    }

    @Test
    public void stopTest() {
        currentState.stop();
        assertEquals(currentState.getDecision().getAction(), Action.STOP);
    }

    @Test
    public void headingTest() {
        currentState.heading(Direction.WEST);
        assertEquals(currentState.getDecision().getAction(), Action.HEADING);
    }

    @Test 
    public void dequeueTest() {
        currentState.fly();
        Decision decision = currentState.getDecision();
        assertNotNull(decision);
        assertEquals(currentState.getDecision().getAction(), Action.FLY);
    }

}