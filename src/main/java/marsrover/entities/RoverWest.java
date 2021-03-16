package marsrover.entities;

import marsrover.entities.enums.CompassDirection;
import marsrover.entities.enums.MovementAction;

public class RoverWest extends Rover {
    public RoverWest(Plateau plateau, Position position, Movement movement) {
        super(plateau, new Position(position.getX(), position.getY(), CompassDirection.WEST), movement);
    }

    @Override
    public int getNextX() {
        if (this.getMovement().getCurrentAction() == MovementAction.MOVE_FOWARD) {
            return this.getPosition().getX() - 1;
        } else {
            return this.getPosition().getX();
        }
    }

    @Override
    public int getNextY() {
        return this.getPosition().getY();
    }
}