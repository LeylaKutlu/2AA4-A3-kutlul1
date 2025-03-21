package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.*;

public class PhaseDecisionMaker extends DecisionMaker {

    private int steps = 0;
    private int x = 1;
    private int y = 1;
    private Queue<String> decisions =  new ArrayDeque<String>();
    private int phase = 1;
    private boolean transition = false; 
    private Creeks creeks = new Creeks();

    // consider making coordinates change in the methods of decision handler instead of here
    @Override
    public Decision decideAction(Drone drone) {
        drone.setCoordinates(x, y);
        if (drone.getBatteryLevel() < 20) {
            DecisionHandler.stop(decision);
            return decision;
        }

        if (phase == 1){
            return phaseOneDecision(drone);
        } else if (phase == 2){
            return phaseTwoDecisions(drone);
        }
        DecisionHandler.stop(decision);
        return decision;
    }

    private Decision phaseOneDecision(Drone drone) {
        if (previousDecision == null){
            DecisionHandler.echo(decision, drone.getDirection());
            return decision;
        }

        if (Action.ECHO.equals(previousDecision.getAction()) && !previousResponse.groundFound() && previousResponse.getRange() == 0){
            decisions.clear();
            DecisionHandler.fly(decision);
            decisions.add(decision.toString());
            DecisionHandler.heading(decision, drone.getDirection());
            decisions.add(decision.toString());
            DecisionHandler.heading(decision, Direction.NORTH);
            y--;
            drone.setDirection(Direction.NORTH);
            phase = 2;
            return decision;
        }

        if (!decisions.isEmpty()){
            decision.setDecision(decisions.poll());
            return decision;
        }

        if (Action.SCAN.equals(previousDecision.getAction())){
            creeks.addCreek(new Creek(previousResponse.getCreek(), x, y));
            DecisionHandler.echo(decision, drone.getDirection());
            decisions.add(decision.toString());
            DecisionHandler.fly(decision);
            x = Direction.EAST.equals(drone.getDirection()) ? x+1 : x-1;
            return decision;
        } 

        if (Action.ECHO.equals(previousDecision.getAction())){
            if (previousResponse.groundFound() && previousResponse.getRange() == 0){
                DecisionHandler.scan(decision);
                return decision;
            }

            int flyCount = previousResponse.groundFound() ? previousResponse.getRange() : previousResponse.getRange() - 1;

            for (int i=1; i<flyCount ; i++){
                DecisionHandler.fly(decision);
                decisions.add(decision.toString());
            }
            
            if (previousResponse.groundFound()){
                DecisionHandler.scan(decision);
                decisions.add(decision.toString());
            } else {
                DecisionHandler.echo(decision, Direction.SOUTH);
                decisions.add(decision.toString());
                DecisionHandler.heading(decision, Direction.SOUTH);
                decisions.add(decision.toString());
                Direction head = drone.getDirection() == Direction.EAST ? Direction.WEST : Direction.EAST;
                DecisionHandler.heading(decision, head);
                decisions.add(decision.toString());
                DecisionHandler.echo(decision, head);
                decisions.add(decision.toString());
                drone.setDirection(head);
                y = y + 2; 
            }

            x = Direction.EAST.equals(drone.getDirection()) ? x + flyCount : x - flyCount;

            DecisionHandler.fly(decision);
            return decision;
        }

        DecisionHandler.echo(decision, drone.getDirection());
        return decision;
    }

    private Decision phaseTwoDecisions(Drone drone){
        if (Action.ECHO.equals(previousDecision.getAction()) && !previousResponse.groundFound() && previousResponse.getRange() == 1){
            decisions.clear();
            DecisionHandler.fly(decision);
            decisions.add(decision.toString());
            DecisionHandler.heading(decision, Direction.SOUTH);
            drone.setDirection(Direction.SOUTH);
            drone.setCoordinates(x,y+1);
            phase = 3;
            return decision;
        }

        if (!decisions.isEmpty()){
            decision.setDecision(decisions.poll());
            return decision;
        }

        if (Action.SCAN.equals(previousDecision.getAction())){
            creeks.addCreek(new Creek(previousResponse.getCreek(), x, y));
            DecisionHandler.echo(decision, drone.getDirection());
            decisions.add(decision.toString());
            DecisionHandler.fly(decision);
            return decision;
        } 

        if (Action.ECHO.equals(previousDecision.getAction())){
            if (previousResponse.groundFound() && previousResponse.getRange() == 0){
                DecisionHandler.scan(decision);
                return decision;
            }

            int flyCount = previousResponse.groundFound() ? previousResponse.getRange() : previousResponse.getRange() - 1;

            for (int i=1; i<flyCount ; i++){
                DecisionHandler.fly(decision);
                decisions.add(decision.toString());
            }
            
            if (previousResponse.groundFound()){
                DecisionHandler.scan(decision);
                decisions.add(decision.toString());
            } else {
                DecisionHandler.echo(decision, Direction.NORTH);
                decisions.add(decision.toString());
                DecisionHandler.heading(decision, Direction.NORTH);
                decisions.add(decision.toString());
                Direction head = drone.getDirection() == Direction.EAST ? Direction.WEST : Direction.EAST;
                DecisionHandler.heading(decision, head);
                decisions.add(decision.toString());
                DecisionHandler.echo(decision, head);
                decisions.add(decision.toString());
                drone.setDirection(head);
            }

            DecisionHandler.fly(decision);
            return decision;
        }

        DecisionHandler.echo(decision, drone.getDirection());
        return decision;
    }

}