package ca.mcmaster.se2aa4.island.teamXXX;

public class PreviousState{
    private Decision prevDecision;
    private Response prevResponse;
    private Direction prevDirection;

    public PreviousState(Decision prevDecision, Response prevResponse, Direction prevDirection){
        this.prevDecision = prevDecision;
        this.prevResponse = prevResponse;
        this.prevDirection = prevDirection;
    }

    public Action getAction(){
        return prevDecision.getAction();
    }

    public boolean isGround(){
        return prevResponse.groundFound();
    }

    public String getCreekId(){
        return prevResponse.getCreek();
    }

    public int getRange(){
        return prevResponse.getRange();
    }

    public void setDirection(Direction direction){
        prevDirection = direction;
    }

    public void setResponse(Response response){
        prevResponse = response;
    }

    public void setDecision(Decision decision){
        prevDecision = decision;
    }

    public boolean foundSite(){
        return prevResponse.foundSite();
    }

    public String getSite(){
        return prevResponse.getSite();
    }
}