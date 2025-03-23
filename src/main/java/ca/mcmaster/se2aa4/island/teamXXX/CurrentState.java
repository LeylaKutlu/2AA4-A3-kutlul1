package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.Queue;
import java.util.ArrayDeque;

public class CurrentState{
    private Decision currentDecision = new Decision();
    private Queue<String> decisions = new ArrayDeque<String>();
    private ActionHandler actionHandler;

    public CurrentState(ActionHandler actionHandler){
        this.actionHandler = actionHandler;
    }

    public void enqueue(){
        decisions.add(currentDecision.toString());
    }

    public Decision dequeue(){
        currentDecision.setDecision(decisions.poll());
        return currentDecision;
    }

    public void clearDecisions(){
        decisions.clear();
    }

    public boolean hasPendingDecisions(){
        return !decisions.isEmpty();
    }

    public void fly(){
        actionHandler.fly(currentDecision);
    }

    public void echo(Direction direction){
        actionHandler.echo(currentDecision, direction);
    }

    public void heading(Direction direction){
        actionHandler.heading(currentDecision, direction);
    }

    public void stop(){
        actionHandler.stop(currentDecision);
    }

    public void scan(){
        actionHandler.scan(currentDecision);
    }

    public Decision getDecision(){
        return currentDecision;
    }
}