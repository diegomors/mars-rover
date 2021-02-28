package entities.enums;

public enum MovementDirection {
    LEFT, RIGHT, MOVE;

    public static MovementDirection fromString(String s) {
      switch(s) {
        case "L": return LEFT;
        case "R": return RIGHT;
        case "M": return MOVE;
        default: throw new IllegalArgumentException();
      }
    }

    @Override
    public String toString() {
      switch(this) {
        case LEFT: return "L";
        case RIGHT: return "R";
        case MOVE: return "M";
        default: throw new IllegalArgumentException();
      }
    }
}
