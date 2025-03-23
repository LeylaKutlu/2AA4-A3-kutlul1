package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.*;

public class PhaseDecisionMaker implements DecisionMaker {
    private int x = 1;
    private int y = 1;
    private int phase = 1;
    private Creeks creeks = new Creeks();
    private DecisionStrategy strategy;
    private CurrentState currentState = new CurrentState(new ActionHandler());

    public PhaseDecisionMaker() {
        setStrategy(new PhaseOneStrategy());
    }
    
    @Override
    public Decision decideAction(Drone drone, PreviousState previous) {
        return strategy.decideAction(drone, this, creeks, previous, currentState);
    }

    private void setStrategy(DecisionStrategy strategy) {
        this.strategy = strategy;
    }

    protected void setPhase(int phase) {
        this.phase = phase;
        if (phase == 2) {
            setStrategy(new PhaseTwoStrategy());
        }
    }
}