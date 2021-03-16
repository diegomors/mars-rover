package marsrover.entities;

public class RoverStarter extends Rover {
    public RoverStarter(Plateau plateau, Position position, Movement movement) {
        super(plateau, position, movement);
    }

    @Override
    public int getNextX() {
        return this.getPosition().getX();
    }

    @Override
    public int getNextY() {
        return this.getPosition().getY();
    }
}