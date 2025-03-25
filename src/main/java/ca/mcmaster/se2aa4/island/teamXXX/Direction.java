package ca.mcmaster.se2aa4.island.teamXXX;

public enum Direction {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private final String direction; 

    Direction(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return direction;  
    }

    public static Direction fromString(String direction){
        for (Direction d : values()){
            if (d.toString().equals(direction)){
                return d;
            }
        }
        return null;
    }
}