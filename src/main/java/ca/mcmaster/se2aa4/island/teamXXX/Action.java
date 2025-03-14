package ca.mcmaster.se2aa4.island.teamXXX;

public enum Action {
    FLY("fly"),
    STOP("stop"),
    ECHO("echo"),
    SCAN("scan"),
    HEADING("heading");

    private final String actionName; 

    Action(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String toString() {
        return actionName;  
    }
}
