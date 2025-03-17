package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.Action;

import java.util.ArrayList;

public class PhaseDecisionMaker extends DecisionMaker{
    private int steps = 0;
    private int x = 1;
    private int y = 1;
    private ArrayList<String> creeks = new ArrayList<String>();
    private int phase = 1;
    private boolean transition = false;

    @Override
    public String decideAction(Drone drone){
        if (phase == 1) {
            logger.info("Phase 1");
            return phaseOneDecision(drone);
        } else if (phase == 2) {
            logger.info("Phase 2");
            return phaseTwoDecision(drone);
        } else {
            logger.info("Phase 3");
            decision.setAction(Action.STOP);
            return decision.toString();
        }
        
    }

    private void setCoordinates(Drone drone){
        if ("N".equals(drone.getDirection())) {
            y++;
        } else if ("E".equals(drone.getDirection())) {
            x++;
        } else if ("W".equals(drone.getDirection())) {
            x--;
        } else if ("S".equals(drone.getDirection())) {
            y--;
        }
    }

    private String phaseOneDecision(Drone drone){
        if (drone.getBatteryLevel() < 20) {
            decision.setAction(Action.STOP);
            return decision.toString();
        } 
        
        if (previousDecision == null){
            setCoordinates(drone);
            decision.setAction(Action.FLY);
            return decision.toString();
        } 

        if (transition && Action.ECHO.equals(previousDecision.getAction())){
            if (!Direction.SOUTH.equals(direction) && previousResponse.getRange() == 1){
                decision.setAction(Action.HEADING);
                parameters.put("direction", Direction.NORTH.toString());
                decision.setParameters(parameters);
                direction = Direction.NORTH;
                drone.setDirection("N");
                y++;
                phase = 2;
                return decision.toString();
            }
            decision.setAction(Action.FLY);
            return decision.toString();
        }

        if (!Direction.SOUTH.equals(direction) && Action.HEADING.equals(previousDecision.getAction())){
            decision.setAction(Action.ECHO);
            parameters.put("direction", Direction.SOUTH.toString());
            decision.setParameters(parameters);
            return decision.toString();
        }

        if (!previousResponse.groundFound() && previousResponse.getRange() <= 1 && !Direction.SOUTH.equals(direction)) {
            if (previousResponse.getRange() == 1) {
                decision.setAction(Action.HEADING);
                parameters.put("direction", Direction.SOUTH.toString()); 
                direction = Direction.SOUTH;
                decision.setParameters(parameters);
                steps++;
                return decision.toString();
            } else if (previousResponse.getRange() == 0) {
                transition = true;
            }
        } 

        if (Direction.SOUTH.equals(direction) && Action.HEADING.equals(previousDecision.getAction())){
            decision.setAction(Action.ECHO);
            parameters.put("direction", Direction.EAST.toString());
            decision.setParameters(parameters);
            return decision.toString();
        }
        
        if (Direction.SOUTH.equals(direction)) {
            decision.setAction(Action.HEADING);
            if (previousResponse.getRange() == 0){
                parameters.put("direction", Direction.WEST.toString());
                direction = Direction.WEST;
                decision.setParameters(parameters);
                drone.setDirection("W");
                y--;
                x--;
            } else {
                parameters.put("direction", Direction.EAST.toString());
                direction = Direction.EAST;
                decision.setParameters(parameters);
                drone.setDirection("E");
                y--;
                x++;
            }
            decision.setParameters(parameters);
            steps++;
            return decision.toString();
        } 

        if (Action.FLY.equals(previousDecision.getAction()) || Action.HEADING.equals(previousDecision.getAction())) {
            decision.setAction(Action.ECHO); 
            parameters.put("direction", direction.toString());
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (Action.SCAN.equals(previousDecision.getAction())){
            if (previousResponse.getCreek() != null) {
                creeks.add(previousResponse.getCreek());
            } 
            decision.setAction(Action.FLY);
            return decision.toString();
        }
        
        if (previousResponse.groundFound() && previousResponse.getRange() == 0) {
            decision.setAction(Action.SCAN);
            return decision.toString();
        } 
        
        decision.setAction(Action.FLY);
        return decision.toString();

    }

    public String phaseTwoDecision(Drone drone){
        if (drone.getBatteryLevel() < 20) {
            decision.setAction(Action.STOP);
            return decision.toString();
        }
        if (transition) {
            if (Direction.NORTH.equals(direction)){
                decision.setAction(Action.FLY);
                transition = false;
                return decision.toString();
            } else if (!Direction.NORTH.equals(direction) && Action.ECHO.equals(previousDecision.getAction()) && previousResponse.getRange() == 1){
                decision.setAction(Action.HEADING);
                parameters.put("direction", Direction.SOUTH.toString());
                decision.setParameters(parameters);
                direction = Direction.SOUTH;
                drone.setDirection("S");
                phase = 3;
                
            }
        }

        if (!Direction.NORTH.equals(direction) && Action.HEADING.equals(previousDecision.getAction())){
            decision.setAction(Action.ECHO);
            parameters.put("direction", Direction.NORTH.toString());
            decision.setParameters(parameters);
            return decision.toString();
        }

        if (!previousResponse.groundFound() && previousResponse.getRange() <= 3 && !Direction.NORTH.equals(direction)) {
            if (previousResponse.getRange() == 1) {
                decision.setAction(Action.HEADING);
                parameters.put("direction", Direction.NORTH.toString()); 
                direction = Direction.NORTH;
                decision.setParameters(parameters);
                steps++;
                return decision.toString();
            } else if (previousResponse.getRange() >= 49) {
                transition = true;
            }
        } 

        if (Direction.NORTH.equals(direction) && Action.HEADING.equals(previousDecision.getAction())){
            decision.setAction(Action.ECHO);
            parameters.put("direction", Direction.WEST.toString());
            decision.setParameters(parameters);
            return decision.toString();
        }
        
        if (Direction.NORTH.equals(direction)) {
            decision.setAction(Action.HEADING);
            if (previousResponse.getRange() == 0){
                parameters.put("direction", Direction.EAST.toString());
                direction = Direction.EAST;
                decision.setParameters(parameters);
                drone.setDirection("E");
                y++;
                x++;
            } else {
                parameters.put("direction", Direction.WEST.toString());
                direction = Direction.WEST;
                decision.setParameters(parameters);
                drone.setDirection("W");
                y++;
                x--;
            }
            decision.setParameters(parameters);
            return decision.toString();
        } 

        if (Action.FLY.equals(previousDecision.getAction()) || Action.HEADING.equals(previousDecision.getAction())) {
            decision.setAction(Action.ECHO); 
            parameters.put("direction", direction.toString());
            decision.setParameters(parameters);
            return decision.toString();
        } 
        
        if (Action.SCAN.equals(previousDecision.getAction())){
            if (previousResponse.getCreek() != null) {
                creeks.add(previousResponse.getCreek());
            } 
            decision.setAction(Action.FLY);
            return decision.toString();
        }
        
        if (previousResponse.groundFound() && previousResponse.getRange() == 0) {
            decision.setAction(Action.SCAN);
            return decision.toString();
        } 
        
        decision.setAction(Action.FLY);
        return decision.toString();

    }

    // public String phaseThreeDecision(Drone drone){
    //     ;
    // }

}