import java.util.List;

import entities.Movement;
import entities.Plateau;
import entities.Position;
import entities.Rover;
import helpers.FileHelper;
import helpers.ParserHelper;
import services.RoverService;

/**
 * Mars Rover
 * @author diegomors
 */
public class App
{
    public static void main(String[] args)
    {
        if (args.length > 0) {
            RoverService service = new RoverService();

            for (String path : args) {
                try {
                    List<String> content = FileHelper.read(path);
                    Plateau plateau = ParserHelper.toPlateau(content.get(0));

                    for (int i = 1; i < content.size()-1; i+=2) {
                        try {
                            Position position = ParserHelper.toPosition(content.get(i));
                            Movement movement = ParserHelper.toMovement(content.get(i+1));
                            Rover rover = new Rover(plateau, position, movement);
                            Position result = service.runScanning(rover);
                            System.out.println(result.toString());
                        } catch (Exception e) {
                            System.out.println(String.format("[%s] %s", e.getClass().getSimpleName(), e.getMessage()));
                        }
                    }
                } catch (Exception e) {
                    System.out.println(String.format("[%s] %s", e.getClass().getSimpleName(), e.getMessage()));
                }
            }
        } else {
            System.out.println("[WARNING] It's necessary to execute with the paths of the input files");
            System.out.println("[WARNING] e.g.: mvn exec:java -Dexec.mainClass=App -Dexec.args=\"C:\\input.txt\"");
        }
    }
}
