package ca.mcmaster.se2aa4.island.teamXXX;

public interface DecisionQueue {
    void enqueue();
    Decision dequeue(Drone drone);
    void clearDecisions();
    boolean hasPendingDecisions();
}