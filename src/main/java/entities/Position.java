package entities;

public class Position {
    private int x;
    private int y;
    private CompassDirection direction;

    public Position(int x, int y, CompassDirection direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CompassDirection getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return String.format("%d %d %s", x, y, direction.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Position.class && o.toString().equals(this.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
