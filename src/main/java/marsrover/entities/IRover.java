package marsrover.entities;

import marsrover.entities.enums.CompassDirection;
import marsrover.exceptions.OutsideMovementException;

public interface IRover {
    int getNextX();
    int getNextY();
    Plateau getPlateau();
    Position getPosition();
    Movement getMovement();
    void setPosition(Position position);
    CompassDirection getNextDirection();
    Position getNextPosition() throws OutsideMovementException;
}
