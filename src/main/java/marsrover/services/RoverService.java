package marsrover.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import marsrover.entities.Movement;
import marsrover.entities.Plateau;
import marsrover.entities.Position;
import marsrover.entities.Rover;
import marsrover.entities.enums.CompassDirection;
import marsrover.entities.enums.MovementAction;
import marsrover.exceptions.OutsideMovementException;
import marsrover.helpers.FileHelper;
import marsrover.helpers.ParserHelper;

@Service
public class RoverService {
    public List<Position> runScanning(String path) throws IOException, OutsideMovementException {
        List<Position> list = new ArrayList<Position>();

        List<String> content = FileHelper.read(path);
        Plateau plateau = ParserHelper.toPlateau(content.get(0));

        for (int i = 1; i < content.size() - 1; i += 2) {
            Position position = ParserHelper.toPosition(content.get(i));
            Movement movement = ParserHelper.toMovement(content.get(i + 1));
            Rover rover = new Rover(plateau, position, movement);
            Position result = runScanning(rover);
            list.add(result);
        }

        return list;
    }

    public Position runScanning(Rover rover) throws OutsideMovementException {
        while (rover.getMovement().moveToNext()) {
            Position position = getNextPosition(rover);
            rover.setPosition(position);
        }

        return rover.getPosition();
    }

    private CompassDirection getNextDirection(Rover rover) {
        MovementAction currentMovement = rover.getMovement().getCurrent();
        CompassDirection currentDirection = rover.getPosition().getDirection();

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

        return currentDirection;
    }

    private Position getNextPosition(Rover rover) throws OutsideMovementException {
        MovementAction currentMovement = rover.getMovement().getCurrent();
        CompassDirection currentDirection = getNextDirection(rover);

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

            if (nextX >= 0 && nextY >= 0 && nextX <= rover.getPlateau().getMaxX()
                    && nextY <= rover.getPlateau().getMaxY()) {
                Position newPosition = new Position(nextX, nextY, currentDirection);
                return newPosition;
            } else {
                throw new OutsideMovementException("Movement outside the plateau isn't allowed");
            }
        } else {
            Position newPosition = new Position(rover.getPosition().getX(), rover.getPosition().getY(),
                    currentDirection);
            return newPosition;
        }
    }
}
