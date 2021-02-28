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
