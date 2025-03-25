package ca.mcmaster.se2aa4.island.teamXXX;

public interface DroneActions {
    void fly();
    void stop();
    void echo(Direction direction);
    void scan();
    void heading(Direction direction);
}