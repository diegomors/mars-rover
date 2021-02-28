package marsrover.entities.enums;

public enum CompassDirection {
    NORTH, EAST, SOUTH, WEST;

    public static CompassDirection fromString(String s) {
      switch(s) {
        case "N": return NORTH;
        case "E": return EAST;
        case "S": return SOUTH;
        case "W": return WEST;
        default: throw new IllegalArgumentException();
      }
    }

    @Override
    public String toString() {
      switch(this) {
        case NORTH: return "N";
        case EAST: return "E";
        case SOUTH: return "S";
        case WEST: return "W";
        default: throw new IllegalArgumentException();
      }
    }
}
