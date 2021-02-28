package marsrover;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import marsrover.entities.Position;
import marsrover.services.RoverService;

/**
 * Mars Rover
 *
 * @author diegomors
 */
@SpringBootApplication
public class App implements ApplicationRunner {
    @Autowired
    private RoverService service;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (String path : args.getSourceArgs()) {
            try {
                List<Position> result = service.runScanning(path);
                for (Position position : result) {
                    System.out.println(position.toString());
                }
            } catch (Exception e) {
                System.out.println(String.format("[%s] %s", e.getClass().getSimpleName(), e.getMessage()));
            }
        }
    }
}
