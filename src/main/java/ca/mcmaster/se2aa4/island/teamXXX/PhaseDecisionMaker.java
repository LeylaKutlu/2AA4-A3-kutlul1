package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.*;

public class PhaseDecisionMaker extends DecisionMaker {

    private int steps = 0;
    private int x = 1;
    private int y = 1;
    private int phase = 1;
    private boolean transition = false; 
    private Creeks creeks = new Creeks();
    private DecisionStrategy strategy;
    private Queue<String> decisions =  new ArrayDeque<String>();

    public PhaseDecisionMaker() {
        setStrategy(new PhaseOneStrategy());
    }
    
    // consider making coordinates change in the methods of decision handler instead of here
    @Override
    public Decision decideAction(Drone drone) {
        return strategy.decideAction(drone, this, creeks);
    }

    public void setStrategy(DecisionStrategy strategy) {
        this.strategy = strategy;
    }

    public void setPhase(int phase) {
        this.phase = phase;
        if (phase == 2) {
            setStrategy(new PhaseTwoStrategy());
        }
    }
    public Queue<String> getDecisions(){
        return decisions;
    }
}