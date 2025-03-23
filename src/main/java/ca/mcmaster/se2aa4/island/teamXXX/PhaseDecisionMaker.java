package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.*;

public class PhaseDecisionMaker implements DecisionMaker {
    private int x = 1;
    private int y = 1;
    private int phase = 1;
    private Creeks creeks = new Creeks();
    private StrategyFactory factory = new StrategyFactory();
    private CurrentState currentState = new CurrentState(new ActionHandler());
    
    @Override
    public Decision decideAction(Drone drone, PreviousState previous) {
        return factory.getStrategy(phase).decideAction(drone, this, creeks, previous, currentState);
    }

    protected void setPhase(int phase) {
        this.phase = phase;
        
    }
}