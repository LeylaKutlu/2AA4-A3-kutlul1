package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class PhaseTwoStrategy implements DecisionStrategy {
    @Override
    public Decision decideAction(Drone drone, PhaseDecisionMaker decisionMaker, Creeks creeks, PreviousState previous, CurrentState currentState) {
        CommandFactory commandFactory = new CommandFactory();

        if (drone.getBatteryLevel() < 20) {
            Command stopCommand = commandFactory.getCommand(Action.STOP, drone, currentState, new JSONObject());
            stopCommand.execute();
            return currentState.getDecision();
        }

        if (Action.ECHO.equals(previous.getAction()) && !previous.isGround() && previous.getRange() == 1){
            currentState.clearDecisions();
            Command stopCommand = commandFactory.getCommand(Action.STOP, drone, currentState, new JSONObject());
            stopCommand.execute();
            return currentState.getDecision();
        }

        if (currentState.hasPendingDecisions()){
            return currentState.dequeue(drone);
        }

        if (Action.SCAN.equals(previous.getAction())){
            creeks.addCreek(new Creek(previous.getCreekId(), drone.getX(), drone.getY()));            
            JSONObject parameters = new JSONObject();
            parameters.put("direction", drone.getDirection().toString());
            Command echoCommand = commandFactory.getCommand(Action.ECHO, drone, currentState, parameters);
            echoCommand.execute();
            currentState.enqueue();
            Command flyCommand = commandFactory.getCommand(Action.FLY, drone, currentState, new JSONObject());
            flyCommand.execute();
            return currentState.getDecision();
        } 

        if (Action.ECHO.equals(previous.getAction())){
            if (previous.isGround() && previous.getRange() == 0){
                Command scanCommand = commandFactory.getCommand(Action.SCAN, drone, currentState, new JSONObject());
                scanCommand.execute();
                return currentState.getDecision();
            }

            int flyCount = previous.getRange() - 1;

            for (int i=1; i<flyCount ; i++){
                Command flyCommand = commandFactory.getCommand(Action.FLY, drone, currentState, new JSONObject());
                flyCommand.execute();
                currentState.enqueue();
            }
            
            if (previous.isGround()){
                Command scanCommand = commandFactory.getCommand(Action.SCAN, drone, currentState, new JSONObject());
                scanCommand.execute();
                currentState.enqueue();
            } else {
                currentState.echo(Direction.NORTH);
                currentState.enqueue();
                currentState.heading(Direction.NORTH);
                currentState.enqueue();
                Direction head = drone.getDirection() == Direction.EAST ? Direction.WEST : Direction.EAST;
                currentState.heading(head);
                currentState.enqueue();
                currentState.echo(head);
                currentState.enqueue();
                drone.setDirection(head);
            }

            Command flyCommand = commandFactory.getCommand(Action.FLY, drone, currentState, new JSONObject());
            flyCommand.execute();
            return currentState.getDecision();
        }

        JSONObject parameters = new JSONObject();
        parameters.put("direction", drone.getDirection().toString());
        Command echoCommand = commandFactory.getCommand(Action.ECHO, drone, currentState, parameters);
        echoCommand.execute();
        return currentState.getDecision();
    }
}