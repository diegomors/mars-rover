import entities.Position;
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
                    Position finalPosition = service.runScanning(path);
                    System.out.println(finalPosition.toString());
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
