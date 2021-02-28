package helpers;

import java.util.ArrayList;
import java.util.List;

import entities.enums.CompassDirection;
import entities.Movement;
import entities.Plateau;
import entities.Position;
import entities.enums.MovementDirection;

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
            List<MovementDirection> list = new ArrayList<MovementDirection>();
            for (char c : input.toCharArray()) {
                list.add(MovementDirection.fromString(String.valueOf(c)));
            }
            return new Movement(list);
        } catch (Exception e) {
            throw new IllegalArgumentException("Input for movement is invalid");
        }
    }
}
