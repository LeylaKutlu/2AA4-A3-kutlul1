package ca.mcmaster.se2aa4.island.teamXXX;

public interface DecisionMaker {
    
    public Decision decideAction(Drone drone, PreviousState previous);
    public Creeks getCreeks();
}