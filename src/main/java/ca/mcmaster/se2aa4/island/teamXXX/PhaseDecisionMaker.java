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

    @Override
    public String decideAction(Drone drone) {
        if (drone.getBatteryLevel() < 20) {
            DecisionHandler.stop(decision);
            return decision.toString();
        }

        if (phase == 1){
            return phaseOneDecision(drone);
        } else if (phase == 2){
            return phaseTwoDecisions(drone);
        }
        DecisionHandler.stop(decision);
        return decision.toString();
    }

    private String phaseOneDecision(Drone drone) {
        if (previousDecision == null){
            DecisionHandler.echo(decision, drone.getDirection());
            return decision.toString();
        }

        if (Action.ECHO.equals(previousDecision.getAction()) && !previousResponse.groundFound() && previousResponse.getRange() == 0){
            decisions.clear();
            DecisionHandler.fly(decision);
            decisions.add(decision.toString());
            DecisionHandler.heading(decision, drone.getDirection());
            decisions.add(decision.toString());
            DecisionHandler.heading(decision, Direction.NORTH);
            phase = 2;
            return decision.toString();
        }

        if (!decisions.isEmpty()){
            decision.setDecision(decisions.poll());
            return decision.toString();
        }

        if (Action.SCAN.equals(previousDecision.getAction())){
            creeks.addCreek(new Creek(previousResponse.getCreek(), x, y));
            DecisionHandler.echo(decision, drone.getDirection());
            decisions.add(decision.toString());
            DecisionHandler.fly(decision);
            return decision.toString();
        } 

        if (Action.ECHO.equals(previousDecision.getAction())){
            if (previousResponse.groundFound() && previousResponse.getRange() == 0){
                DecisionHandler.scan(decision);
                return decision.toString();
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
            }

            x = Direction.EAST.equals(drone.getDirection()) ? x + flyCount : x - flyCount;

            DecisionHandler.fly(decision);
            return decision.toString();
        }

        DecisionHandler.echo(decision, drone.getDirection());
        return decision.toString();
    }

    private String phaseTwoDecisions(Drone drone){
        if (Action.ECHO.equals(previousDecision.getAction()) && !previousResponse.groundFound() && previousResponse.getRange() == 1){
            decisions.clear();
            DecisionHandler.fly(decision);
            decisions.add(decision.toString());
            DecisionHandler.heading(decision, Direction.SOUTH);
            drone.setDirection(Direction.SOUTH);
            phase = 3;
            return decision.toString();
        }

        if (!decisions.isEmpty()){
            decision.setDecision(decisions.poll());
            return decision.toString();
        }

        if (Action.SCAN.equals(previousDecision.getAction())){
            creeks.addCreek(new Creek(previousResponse.getCreek(), x, y));
            DecisionHandler.echo(decision, drone.getDirection());
            decisions.add(decision.toString());
            DecisionHandler.fly(decision);
            return decision.toString();
        } 

        if (Action.ECHO.equals(previousDecision.getAction())){
            if (previousResponse.groundFound() && previousResponse.getRange() == 0){
                DecisionHandler.scan(decision);
                return decision.toString();
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

            x = Direction.EAST.equals(drone.getDirection()) ? x + flyCount : x - flyCount;

            DecisionHandler.fly(decision);
            return decision.toString();
        }

        DecisionHandler.echo(decision, drone.getDirection());
        return decision.toString();
    }

}