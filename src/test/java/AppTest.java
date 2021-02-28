import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Test;

import entities.CompassDirection;
import entities.Movement;
import entities.Plateau;
import entities.Position;
import entities.Rover;
import exceptions.OutsideMovementException;
import helpers.FileHelper;
import helpers.ParserHelper;
import services.RoverService;

/**
 * Unit test for Mars Rover
 *
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
        Movement expected = new Movement(Arrays.asList("LMLMLMLMM"));

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
        Movement movement1 = new Movement(Arrays.asList("LMLMLMLMM"));
        Rover rover1 = new Rover(plateau1, position1, movement1);

        Position expected1 = new Position(1, 3, CompassDirection.NORTH);

        Position result1 = service.runScanning(rover1);

        assertTrue(result1.equals(expected1));

        Plateau plateau2 = new Plateau(5, 5);
        Position position2 = new Position(3, 3, CompassDirection.EAST);
        Movement movement2 = new Movement(Arrays.asList("MMRMMRMRRM"));
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
        Movement movement = new Movement(Arrays.asList("LMMLMLMLMM"));
        Rover rover = new Rover(plateau, position, movement);

        service.runScanning(rover);
    }

    @Test
    public void shouldReadFileCorrectly() throws FileNotFoundException
    {
        String[] result = FileHelper.read("input.txt");

        String[] expected = {"5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM"};

        assertTrue(result.equals(expected));
    }

    @Test(expected = OutsideMovementException.class)
    public void shouldThrowFileNotFound() throws FileNotFoundException
    {
        FileHelper.read("notfound.txt");
    }
}
