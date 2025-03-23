package ca.mcmaster.se2aa4.island.teamXXX;

public interface DecisionStrategy {
    Decision decideAction(Drone drone, PhaseDecisionMaker decisionMaker, Creeks creeks, PreviousState previousState, CurrentState currentState);
}