package ca.mcmaster.se2aa4.island.teamXXX;

public class PhaseTwoStrategy implements DecisionStrategy {
    @Override
    public Decision decideAction(Drone drone, PhaseDecisionMaker decisionMaker, Creeks creeks, PreviousState previous, CurrentState currentState) {
        if (drone.getBatteryLevel() < 20) {
            currentState.stop();
            return currentState.getDecision();
        }

        if (Action.ECHO.equals(previous.getAction()) && !previous.isGround() && previous.getRange() == 1){
            currentState.clearDecisions();
            currentState.stop();
            return currentState.getDecision();
        }

        if (currentState.hasPendingDecisions()){
            return currentState.dequeue();
        }

        if (Action.SCAN.equals(previous.getAction())){
            creeks.addCreek(new Creek(previous.getCreekId(), drone.getX(), drone.getY()));
            currentState.echo(drone.getDirection());
            currentState.enqueue();
            currentState.fly();
            return currentState.getDecision();
        } 

        if (Action.ECHO.equals(previous.getAction())){
            if (previous.isGround() && previous.getRange() == 0){
                currentState.scan();
                return currentState.getDecision();
            }

            int flyCount = previous.getRange() - 1;

            for (int i=1; i<flyCount ; i++){
                currentState.fly();
                currentState.enqueue();
            }
            
            if (previous.isGround()){
                currentState.scan();
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

            currentState.fly();
            return currentState.getDecision();
        }

        currentState.echo(drone.getDirection());
        return currentState.getDecision();
    }
}