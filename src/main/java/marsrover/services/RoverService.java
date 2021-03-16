package marsrover.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import marsrover.entities.Movement;
import marsrover.entities.Plateau;
import marsrover.entities.Position;
import marsrover.entities.Rover;
import marsrover.entities.RoverStarter;
import marsrover.exceptions.OutsideMovementException;
import marsrover.factory.RoverFactory;
import marsrover.helpers.FileHelper;
import marsrover.helpers.ParserHelper;

@Service
public class RoverService {
    public List<Position> runScanning(String path) throws IOException, OutsideMovementException {
        List<Position> list = new ArrayList<Position>();

        List<String> content = FileHelper.read(path);
        Plateau plateau = ParserHelper.toPlateau(content.get(0));

        for (int i = 1; i < content.size() - 1; i += 2) {
            Position position = ParserHelper.toPosition(content.get(i));
            Movement movement = ParserHelper.toMovement(content.get(i + 1));
            Rover rover = new RoverStarter(plateau, position, movement);
            Position result = runScanning(rover);
            list.add(result);
        }

        return list;
    }

    public Position runScanning(Rover rover) throws OutsideMovementException {
        while (rover.getMovement().hasNextAction(true)) {
            rover = RoverFactory.getNextBehavior(rover);
            Position position = rover.getNextPosition();
            rover.setPosition(position);
        }

        return rover.getPosition();
    }
}
