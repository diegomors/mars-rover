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
}
