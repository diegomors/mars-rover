package marsrover.factory;

import marsrover.entities.Rover;
import marsrover.entities.RoverEast;
import marsrover.entities.RoverNorth;
import marsrover.entities.RoverSouth;
import marsrover.entities.RoverWest;

public class RoverFactory {
    public static Rover getNextBehavior(Rover rover) {
        switch (rover.getNextDirection().ordinal()) {
            case 0:
                return new RoverNorth(rover.getPlateau(), rover.getPosition(), rover.getMovement());
            case 1:
                return new RoverEast(rover.getPlateau(), rover.getPosition(), rover.getMovement());
            case 2:
                return new RoverSouth(rover.getPlateau(), rover.getPosition(), rover.getMovement());
            case 3:
                return new RoverWest(rover.getPlateau(), rover.getPosition(), rover.getMovement());
            default:
                throw new IllegalArgumentException();
        }
    }
}
