package marsrover.entities;

import marsrover.entities.enums.CompassDirection;
import marsrover.entities.enums.MovementAction;

public class RoverEast extends Rover {
    public RoverEast(Plateau plateau, Position position, Movement movement) {
        super(plateau, new Position(position.getX(), position.getY(), CompassDirection.EAST), movement);
    }

    @Override
    public int getNextX() {
        if (this.getMovement().getCurrentAction() == MovementAction.MOVE_FOWARD) {
            return this.getPosition().getX() + 1;
        } else {
            return this.getPosition().getX();
        }
    }

    @Override
    public int getNextY() {
        return this.getPosition().getY();
    }
}