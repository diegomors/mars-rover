package marsrover.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {
    public static List<String> read(String path) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }
}
