import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import entities.enums.CompassDirection;
import entities.enums.MovementDirection;
import entities.Movement;
import entities.Plateau;
import entities.Position;
import entities.Rover;
import exceptions.OutsideMovementException;
import helpers.ParserHelper;
import services.RoverService;

/**
 * Unit test for Mars Rover
 * @author diegomors
 */
public class AppTest {
    @Test
    public void shouldConvertInputToValidPlateau() {
        String input = "5 5";
        Plateau expected = new Plateau(5, 5);

        Plateau result = ParserHelper.toPlateau(input);

        assertTrue(result.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldInvalidateInputForPlateau() {
        ParserHelper.toPlateau("55");
        ParserHelper.toPlateau("5 5 5");
        ParserHelper.toPlateau("5 N");
    }

    @Test
    public void shouldConvertInputToValidPosition() {
        String input = "1 2 N";
        Position expected = new Position(1, 2, CompassDirection.NORTH);

        Position result = ParserHelper.toPosition(input);

        assertTrue(result.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldInvalidateInputForPosition() {
        ParserHelper.toPosition("55");
        ParserHelper.toPosition("5 5 5");
        ParserHelper.toPosition("5 N");
    }

    @Test
    public void shouldConvertInputToValidMovement() {
        String input = "LMLMLMLMM";

        List<MovementDirection> movs = new ArrayList<MovementDirection>(Arrays.asList(MovementDirection.LEFT,
            MovementDirection.MOVE, MovementDirection.LEFT, MovementDirection.MOVE, MovementDirection.LEFT,
            MovementDirection.MOVE, MovementDirection.LEFT, MovementDirection.MOVE, MovementDirection.MOVE));
        Movement expected = new Movement(movs);

        Movement result = ParserHelper.toMovement(input);

        assertTrue(result.equals(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldInvalidateInputForMovement() {
        ParserHelper.toMovement("55");
        ParserHelper.toMovement("5 5 5");
        ParserHelper.toMovement("5 N");
    }

    @Test
    public void shouldReturnCorrectFinalPosition() {
        RoverService service = new RoverService();

        Plateau plateau1 = new Plateau(5, 5);
        Position position1 = new Position(1, 2, CompassDirection.NORTH);
        List<MovementDirection> movs1 = new ArrayList<MovementDirection>(Arrays.asList(MovementDirection.LEFT,
            MovementDirection.MOVE, MovementDirection.LEFT, MovementDirection.MOVE, MovementDirection.LEFT,
            MovementDirection.MOVE, MovementDirection.LEFT, MovementDirection.MOVE, MovementDirection.MOVE));
        Movement movement1 = new Movement(movs1);
        Rover rover1 = new Rover(plateau1, position1, movement1);

        Position expected1 = new Position(1, 3, CompassDirection.NORTH);

        Position result1 = service.runScanning(rover1);

        assertTrue(result1.equals(expected1));

        Plateau plateau2 = new Plateau(5, 5);
        Position position2 = new Position(3, 3, CompassDirection.EAST);
        List<MovementDirection> movs2 = new ArrayList<MovementDirection>(Arrays.asList(MovementDirection.MOVE,
            MovementDirection.MOVE, MovementDirection.RIGHT, MovementDirection.MOVE, MovementDirection.MOVE,
            MovementDirection.RIGHT, MovementDirection.MOVE, MovementDirection.RIGHT, MovementDirection.RIGHT,
            MovementDirection.MOVE));
        Movement movement2 = new Movement(movs2);
        Rover rover2 = new Rover(plateau2, position2, movement2);

        Position expected2 = new Position(5, 1, CompassDirection.EAST);

        Position result2 = service.runScanning(rover2);

        assertTrue(result2.equals(expected2));
    }

    @Test(expected = OutsideMovementException.class)
    public void shouldInvalidateOutsideMovement() {
        RoverService service = new RoverService();

        Plateau plateau = new Plateau(5, 5);
        Position position = new Position(1, 2, CompassDirection.NORTH);
        List<MovementDirection> movs = new ArrayList<MovementDirection>(Arrays.asList(MovementDirection.LEFT,
            MovementDirection.MOVE, MovementDirection.MOVE, MovementDirection.MOVE, MovementDirection.LEFT,
            MovementDirection.MOVE, MovementDirection.LEFT, MovementDirection.MOVE, MovementDirection.MOVE));
        Movement movement = new Movement(movs);
        Rover rover = new Rover(plateau, position, movement);

        service.runScanning(rover);
    }
}
