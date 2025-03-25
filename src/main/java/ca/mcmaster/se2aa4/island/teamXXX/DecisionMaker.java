package ca.mcmaster.se2aa4.island.teamXXX;

public interface DecisionMaker {
    
    Decision decideAction(Drone drone, PreviousState previous);
    Creeks getCreeks();
}