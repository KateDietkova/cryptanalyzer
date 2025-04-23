package util;

import core.CryptAnalyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileWriterUtils {

    public static void write (String filePath, String fileContent, CryptAnalyzer.Mode mode) {
        try {
            Path path = Path.of(filePath);

            if (!path.isAbsolute()) {
                path = path.toAbsolutePath();
            }

            String fileName = path.getFileName().toString();
            String fileExtension = "";

            int fileExtensionIdx = fileName.lastIndexOf(".");

            if(fileExtensionIdx > 0) {
                fileExtension = fileName.substring(fileExtensionIdx);
                fileName = fileName.substring(0, fileExtensionIdx);
            }

            fileName = fileName.replace("[" + CryptAnalyzer.Mode.ENCRYPT + "ED]", "");

            String suffix = "[" + mode + "ED]";
            String newFileName = fileName + suffix + fileExtension;
            Path newFilePath = path.resolveSibling(newFileName);

            try(BufferedWriter writer = Files.newBufferedWriter(newFilePath)) {
                writer.write(fileContent);

            } catch (IOException e) {
                System.out.println("Something went wrong with file writing" + e.getMessage());
            }
        } catch (InvalidPathException e) {
            System.out.println("Incorrect file's path" + e.getMessage());
        }
    }
}
