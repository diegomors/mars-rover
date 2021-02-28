package marsrover.helpers;

import java.util.ArrayList;
import java.util.List;

import marsrover.entities.Movement;
import marsrover.entities.Plateau;
import marsrover.entities.Position;
import marsrover.entities.enums.CompassDirection;
import marsrover.entities.enums.MovementAction;

public class ParserHelper {
    public static Plateau toPlateau(String input) throws IllegalArgumentException {
        try {
            String[] array = input.split(" ");
            if (array.length != 2) throw new IllegalArgumentException();
            int x = Integer.parseInt(array[0]);
            int y = Integer.parseInt(array[1]);
            return new Plateau(x, y);
        } catch (Exception e) {
            throw new IllegalArgumentException("Input for plateau is invalid");
        }
    }

    public static Position toPosition(String input) throws IllegalArgumentException {
        try {
            String[] array = input.split(" ");
            if (array.length != 3) throw new IllegalArgumentException();
            int x = Integer.parseInt(array[0]);
            int y = Integer.parseInt(array[1]);
            CompassDirection d = CompassDirection.fromString(array[2]);
            return new Position(x, y, d);
        } catch (Exception e) {
            throw new IllegalArgumentException("Input for position is invalid");
        }
    }

    public static Movement toMovement(String input) throws IllegalArgumentException {
        try {
            List<MovementAction> list = new ArrayList<MovementAction>();
            for (char c : input.toCharArray()) {
                list.add(MovementAction.fromString(String.valueOf(c)));
            }
            return new Movement(list);
        } catch (Exception e) {
            throw new IllegalArgumentException("Input for movement is invalid");
        }
    }
}
