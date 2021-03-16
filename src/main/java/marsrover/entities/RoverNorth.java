package marsrover.entities;

import marsrover.entities.enums.CompassDirection;
import marsrover.entities.enums.MovementAction;

public class RoverNorth extends Rover {
    public RoverNorth(Plateau plateau, Position position, Movement movement) {
        super(plateau, new Position(position.getX(), position.getY(), CompassDirection.NORTH), movement);
    }

    @Override
    public int getNextX() {
        return this.getPosition().getX();
    }

    @Override
    public int getNextY() {
        if (this.getMovement().getCurrentAction() == MovementAction.MOVE_FOWARD) {
            return this.getPosition().getY() + 1;
        } else {
            return this.getPosition().getY();
        }
    }
}
