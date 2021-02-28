package marsrover;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import marsrover.entities.Movement;
import marsrover.entities.Plateau;
import marsrover.entities.Position;
import marsrover.entities.Rover;
import marsrover.entities.enums.CompassDirection;
import marsrover.entities.enums.MovementAction;
import marsrover.exceptions.OutsideMovementException;
import marsrover.helpers.ParserHelper;
import marsrover.services.RoverService;

/**
 * Unit tests for Mars Rover
 *
 * @author diegomors
 */
@SpringBootTest
public class AppTest {

    @Autowired
    RoverService service;

    public static Rover getRoverA() {
        Plateau plateau = new Plateau(5, 5);
        Position position = new Position(1, 2, CompassDirection.NORTH);
        List<MovementAction> movs = new ArrayList<MovementAction>(
                Arrays.asList(MovementAction.TURN_LEFT, MovementAction.MOVE_FOWARD, MovementAction.TURN_LEFT,
                        MovementAction.MOVE_FOWARD, MovementAction.TURN_LEFT, MovementAction.MOVE_FOWARD,
                        MovementAction.TURN_LEFT, MovementAction.MOVE_FOWARD, MovementAction.MOVE_FOWARD));
        Movement movement = new Movement(movs);
        Rover rover = new Rover(plateau, position, movement);

        return rover;
    }

    public static Rover getRoverB() {
        Plateau plateau = new Plateau(5, 5);
        Position position = new Position(3, 3, CompassDirection.EAST);
        List<MovementAction> movs = new ArrayList<MovementAction>(Arrays.asList(MovementAction.MOVE_FOWARD,
                MovementAction.MOVE_FOWARD, MovementAction.TURN_RIGHT, MovementAction.MOVE_FOWARD,
                MovementAction.MOVE_FOWARD, MovementAction.TURN_RIGHT, MovementAction.MOVE_FOWARD,
                MovementAction.TURN_RIGHT, MovementAction.TURN_RIGHT, MovementAction.MOVE_FOWARD));
        Movement movement = new Movement(movs);
        Rover rover = new Rover(plateau, position, movement);

        return rover;
    }

    public static Rover getRoverWrong() {
        Plateau plateau = new Plateau(5, 5);
        Position position = new Position(1, 2, CompassDirection.NORTH);
        List<MovementAction> movs = new ArrayList<MovementAction>(
                Arrays.asList(MovementAction.TURN_LEFT, MovementAction.MOVE_FOWARD, MovementAction.MOVE_FOWARD,
                        MovementAction.MOVE_FOWARD, MovementAction.TURN_LEFT, MovementAction.MOVE_FOWARD,
                        MovementAction.TURN_LEFT, MovementAction.MOVE_FOWARD, MovementAction.MOVE_FOWARD));
        Movement movement = new Movement(movs);
        Rover rover = new Rover(plateau, position, movement);

        return rover;
    }

    @ParameterizedTest
    @ValueSource(strings = { "5 5" })
    @DisplayName("Test for valid input conversion to Plateau object")
    public void shouldConvertInputToValidPlateau(String input) {
        Plateau expected = new Plateau(5, 5);
        Plateau result = ParserHelper.toPlateau(input);
        assertTrue(result.equals(expected));
    }

    @ParameterizedTest
    @ValueSource(strings = { "55", "5 5 5", "5 N" })
    @DisplayName("Test for invalid input conversion to Plateau object")
    public void shouldInvalidateInputForPlateau(String input) {
        assertThrows(IllegalArgumentException.class, () -> ParserHelper.toPlateau(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "1 2 N" })
    @DisplayName("Test for valid input conversion to Position object")
    public void shouldConvertInputToValidPosition(String input) {
        Position expected = new Position(1, 2, CompassDirection.NORTH);
        Position result = ParserHelper.toPosition(input);
        assertTrue(result.equals(expected));
    }

    @ParameterizedTest
    @ValueSource(strings = { "55", "5 5 5", "5 N" })
    @DisplayName("Test for invalid input conversion to Position object")
    public void shouldInvalidateInputForPosition(String input) {
        assertThrows(IllegalArgumentException.class, () -> ParserHelper.toPosition(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "LMLMLMLMM" })
    @DisplayName("Test for valid input conversion to Movement object")
    public void shouldConvertInputToValidMovement(String input) {
        Movement expected = getRoverA().getMovement();
        Movement result = ParserHelper.toMovement(input);
        assertTrue(result.equals(expected));
    }

    @ParameterizedTest
    @ValueSource(strings = { "55", "5 5 5", "5 N" })
    @DisplayName("Test for invalid input conversion to Movement object")
    public void shouldInvalidateInputForMovement(String input) {
        assertThrows(IllegalArgumentException.class, () -> ParserHelper.toMovement(input));
    }

    @Test
    @DisplayName("Test for scanning execution to valid two Rover")
    public void shouldReturnCorrectFinalPosition() throws OutsideMovementException {
        Rover roverA = getRoverA();
        Position expectedA = new Position(1, 3, CompassDirection.NORTH);
        Position resultA = service.runScanning(roverA);

        Rover roverB = getRoverB();
        Position expectedB = new Position(5, 1, CompassDirection.EAST);
        Position resultB = service.runScanning(roverB);

        assertAll("rovers", () -> assertTrue(resultA.equals(expectedA)), () -> assertTrue(resultB.equals(expectedB)));
    }

    @Test
    @DisplayName("Test for scanning execution to Rover that's try to move outside the Plateau")
    public void shouldInvalidateOutsideMovement() throws OutsideMovementException {
        Rover rover = getRoverWrong();
        assertThrows(OutsideMovementException.class, () -> service.runScanning(rover));
    }
}
