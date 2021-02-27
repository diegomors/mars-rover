package entities;

public class Rover {
    private Plateau plateau;
    private Position position;
    private Movement movement;

    public Rover(Plateau plateau, Position position, Movement movement) {
        this.plateau = plateau;
        this.position = position;
        this.movement = movement;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Movement getMovement() {
        return movement;
    }
}
