package marsrover.entities;

import java.util.Arrays;

import marsrover.entities.enums.CompassDirection;
import marsrover.entities.enums.MovementAction;
import marsrover.exceptions.OutsideMovementException;

public abstract class Rover implements IRover {
    private Plateau plateau;
    private Position position;
    private Movement movement;

    protected Rover(Plateau plateau, Position position, Movement movement) {
        this.plateau = plateau;
        this.position = position;
        this.movement = movement;
    }

    @Override
    public Plateau getPlateau() {
        return plateau;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Movement getMovement() {
        return movement;
    }

    @Override
    public CompassDirection getNextDirection() {
        CompassDirection[] compass = CompassDirection.values();

        int index = 0;
        int stepsTo = 0;
        int startAt = Arrays.asList(compass).indexOf(this.getPosition().getDirection());
        if (this.getMovement().getCurrentAction() == MovementAction.TURN_LEFT) {
            stepsTo = startAt > 0 ? -1 : 3;
        } else if (this.getMovement().getCurrentAction() == MovementAction.TURN_RIGHT) {
            stepsTo = startAt == 3 ? -3 : 1;
        }
        index = startAt + stepsTo;

        return compass[index];
    }

    @Override
    public Position getNextPosition() throws OutsideMovementException {
        int nextX = this.getNextX();
        int nextY = this.getNextY();

        if (nextX >= 0 && nextY >= 0 && nextX <= this.getPlateau().getMaxX()
                && nextY <= this.getPlateau().getMaxY()) {
            Position newPosition = new Position(nextX, nextY, this.getPosition().getDirection());
            return newPosition;
        } else {
            throw new OutsideMovementException("Movement outside the plateau isn't allowed");
        }
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%s",
            this.getPlateau().toString(),
            this.getPosition().toString(),
            this.getMovement().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Rover.class && ((Rover) o).toString().equals(this.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
