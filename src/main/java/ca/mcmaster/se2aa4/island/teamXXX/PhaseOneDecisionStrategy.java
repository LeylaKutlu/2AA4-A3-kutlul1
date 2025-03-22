package ca.mcmaster.se2aa4.island.teamXXX;

public class PhaseOneDecisionStrategy implements DecisionStrategy {
    @Override
    public Decision decideAction(Drone drone, PhaseDecisionMaker decisionMaker, Creeks creeks) {
        if (decisionMaker.getPrevDecision() == null){
            DecisionHandler.echo(decisionMaker.getDecision(), drone.getDirection());
            return decisionMaker.getDecision();
        }

        if (Action.ECHO.equals(decisionMaker.getPrevDecision().getAction()) && !decisionMaker.getPrevResponse().groundFound() && decisionMaker.getPrevResponse().getRange() == 0){
            decisionMaker.getDecisions().clear();
            DecisionHandler.fly(decisionMaker.getDecision());
            decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
            DecisionHandler.heading(decisionMaker.getDecision(), drone.getDirection());
            decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
            DecisionHandler.heading(decisionMaker.getDecision(), Direction.NORTH);
            drone.setDirection(Direction.NORTH);
            decisionMaker.setPhase(2);
            return decisionMaker.getDecision();
        }

        if (!decisionMaker.getDecisions().isEmpty()){
            decisionMaker.getDecision().setDecision(decisionMaker.getDecisions().poll());
            return decisionMaker.getDecision();
        }

        if (Action.SCAN.equals(decisionMaker.getPrevDecision().getAction())){
            creeks.addCreek(new Creek(decisionMaker.getPrevResponse().getCreek(), 1, 1));
            DecisionHandler.echo(decisionMaker.getDecision(), drone.getDirection());
            decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
            DecisionHandler.fly(decisionMaker.getDecision());
            // x = Direction.EAST.equals(drone.getDirection()) ? x+1 : x-1;
            return decisionMaker.getDecision();
        } 

        if (Action.ECHO.equals(decisionMaker.getPrevDecision().getAction())){
            if (decisionMaker.getPrevResponse().groundFound() && decisionMaker.getPrevResponse().getRange() == 0){
                DecisionHandler.scan(decisionMaker.getDecision());
                // decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
                return decisionMaker.getDecision();
            }

            int flyCount = decisionMaker.getPrevResponse().groundFound() ? decisionMaker.getPrevResponse().getRange() -1: decisionMaker.getPrevResponse().getRange() - 1;

            for (int i=1; i<flyCount ; i++){
                DecisionHandler.fly(decisionMaker.getDecision());
                decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
            }
            
            if (decisionMaker.getPrevResponse().groundFound()){
                DecisionHandler.scan(decisionMaker.getDecision());
                decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
            } else {
                DecisionHandler.echo(decisionMaker.getDecision(), Direction.SOUTH);
                decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
                DecisionHandler.heading(decisionMaker.getDecision(), Direction.SOUTH);
                decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
                Direction head = drone.getDirection() == Direction.EAST ? Direction.WEST : Direction.EAST;
                DecisionHandler.heading(decisionMaker.getDecision(), head);
                decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
                DecisionHandler.echo(decisionMaker.getDecision(), head);
                decisionMaker.getDecisions().add(decisionMaker.getDecision().toString());
                drone.setDirection(head);
                // y = y + 2; 
            }

            // x = Direction.EAST.equals(drone.getDirection()) ? x + flyCount : x - flyCount;

            DecisionHandler.fly(decisionMaker.getDecision());
            return decisionMaker.getDecision();
        }

        DecisionHandler.echo(decisionMaker.getDecision(), drone.getDirection());
        return decisionMaker.getDecision();
    }
}