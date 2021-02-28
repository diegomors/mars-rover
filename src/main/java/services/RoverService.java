package services;

import entities.Position;
import entities.Rover;
import entities.enums.CompassDirection;
import entities.enums.MovementAction;
import exceptions.OutsideMovementException;

public class RoverService {
    public Position runScanning(Rover rover) throws OutsideMovementException {
        while (rover.getMovement().moveToNext()) {
            CompassDirection currentDirection = rover.getPosition().getDirection();
            MovementAction currentMovement = rover.getMovement().getCurrent();

            switch (currentDirection.ordinal()) {
                case 0:
                    // NORTH
                    if (currentMovement == MovementAction.TURN_LEFT) {
                        currentDirection = CompassDirection.WEST;
                    } else if (currentMovement == MovementAction.TURN_RIGHT) {
                        currentDirection = CompassDirection.EAST;
                    } else {
                        currentDirection = CompassDirection.NORTH;
                    }
                    break;
                case 1:
                    // EAST
                    if (currentMovement == MovementAction.TURN_LEFT) {
                        currentDirection = CompassDirection.NORTH;
                    } else if (currentMovement == MovementAction.TURN_RIGHT) {
                        currentDirection = CompassDirection.SOUTH;
                    } else {
                        currentDirection = CompassDirection.EAST;
                    }
                    break;
                case 2:
                    // SOUTH
                    if (currentMovement == MovementAction.TURN_LEFT) {
                        currentDirection = CompassDirection.EAST;
                    } else if (currentMovement == MovementAction.TURN_RIGHT) {
                        currentDirection = CompassDirection.WEST;
                    } else {
                        currentDirection = CompassDirection.SOUTH;
                    }
                    break;
                case 3:
                    // WEST
                    if (currentMovement == MovementAction.TURN_LEFT) {
                        currentDirection = CompassDirection.SOUTH;
                    } else if (currentMovement == MovementAction.TURN_RIGHT) {
                        currentDirection = CompassDirection.NORTH;
                    } else {
                        currentDirection = CompassDirection.WEST;
                    }
                    break;
                default:
                    break;
            }

            int nextX, nextY;
            if (currentMovement == MovementAction.MOVE_FOWARD) {
                switch (currentDirection.ordinal()) {
                    case 0:
                        // NORTH
                        nextX = rover.getPosition().getX();
                        nextY = rover.getPosition().getY() + 1;
                        break;
                    case 1:
                        // EAST
                        nextX = rover.getPosition().getX() + 1;
                        nextY = rover.getPosition().getY();
                        break;
                    case 2:
                        // SOUTH
                        nextX = rover.getPosition().getX();
                        nextY = rover.getPosition().getY() - 1;
                        break;
                    case 3:
                        // WEST
                        nextX = rover.getPosition().getX() - 1;
                        nextY = rover.getPosition().getY();
                        break;
                    default:
                        nextX = rover.getPosition().getX();
                        nextY = rover.getPosition().getY();
                        break;
                }

                if (nextX >= 0 && nextY >= 0
                    && nextX <= rover.getPlateau().getMaxX()
                    && nextY <= rover.getPlateau().getMaxY()) {
                    Position newPosition = new Position(nextX, nextY, currentDirection);
                    rover.setPosition(newPosition);
                } else {
                    throw new OutsideMovementException("Movement outside the plateau isn't allowed");
                }
            } else {
                Position newPosition = new Position(rover.getPosition().getX(),
                    rover.getPosition().getY(), currentDirection);
                rover.setPosition(newPosition);
            }
        }

        return rover.getPosition();
    }
}
