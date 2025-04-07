package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileReaderUtils {
    public static String read(String filePath) {
        try {
            Path path = Path.of(filePath);

            if (!path.isAbsolute()) {
                path = path.toAbsolutePath();
            }


            try(BufferedReader reader = Files.newBufferedReader(path)) {
                StringBuilder strBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    strBuilder.append(line).append("/n");
                }
                 return strBuilder.toString();

            } catch (IOException e) {
                System.out.println("Something went wrong with file reading" + e.getMessage());
            }
        } catch (InvalidPathException e) {
            System.out.println("Incorrect file's path" + e.getMessage());
        }
        return null;
    }
}
