package ca.mcmaster.se2aa4.island.teamXXX;

public class PhaseDecisionMaker implements DecisionMaker {
    private int phase = 1;
    private Creeks creeks = new Creeks();
    private StrategyFactory factory = new StrategyFactory();
    private CurrentState currentState = new CurrentState();
    
    @Override
    public Decision decideAction(Drone drone, PreviousState previous) {
        return factory.getStrategy(phase).decideAction(drone, this, creeks, previous, currentState);
    }

    @Override
    public Creeks getCreeks() {
        return creeks;
    }
    protected void setPhase(int phase) {
        this.phase = phase;
        
    }
}