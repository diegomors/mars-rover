package entities;

public class Plateau {
    private int maxX;
    private int maxY;

    public Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    @Override
    public String toString() {
        return String.format("%d %d", maxX, maxY);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Plateau.class && o.toString().equals(this.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
