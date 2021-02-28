package marsrover.entities.enums;

public enum MovementAction {
    TURN_LEFT, TURN_RIGHT, MOVE_FOWARD;

    public static MovementAction fromString(String s) {
      switch(s) {
        case "L": return TURN_LEFT;
        case "R": return TURN_RIGHT;
        case "M": return MOVE_FOWARD;
        default: throw new IllegalArgumentException();
      }
    }

    @Override
    public String toString() {
      switch(this) {
        case TURN_LEFT: return "L";
        case TURN_RIGHT: return "R";
        case MOVE_FOWARD: return "M";
        default: throw new IllegalArgumentException();
      }
    }
}
