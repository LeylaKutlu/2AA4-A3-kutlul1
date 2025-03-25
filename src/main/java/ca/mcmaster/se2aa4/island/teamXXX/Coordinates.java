package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Coordinates{
    private int x;
    private int y;
    private final Logger logger = LogManager.getLogger();

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    private void notifyObservers(){
        for(Observer observer : observers){
            observer.updateCoordinates(x, y);
        }
    }

    public void update(PreviousState previous, Direction prevDirection, Direction currDirection){
        if (!Action.FLY.equals(previous.getAction()) && !Action.HEADING.equals(previous.getAction())){
            return;
        }

        if (Direction.NORTH.equals(prevDirection) || Direction.NORTH.equals(currDirection)){
            y--;
        } 

        if (Direction.SOUTH.equals(prevDirection) || Direction.SOUTH.equals(currDirection)){
            y++;
        }

        if (Direction.EAST.equals(prevDirection) || Direction.EAST.equals(currDirection)){
            x++;
        }
        
        if (Direction.WEST.equals(prevDirection) || Direction.WEST.equals(currDirection)){
            x--;
        }

        logger.info("Coordinates updated to: (" + x + ", " + y + ")");
        notifyObservers();
    }

}