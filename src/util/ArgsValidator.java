package util;

import core.CryptAnalyzer;
import core.entity.CryptParams;

import java.io.File;
import java.nio.file.FileSystemNotFoundException;

public class ArgsValidator {
    public static CryptParams checkAndConvert(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Expected 3 arguments: <ENCRYPT/DECRYPT> <filePath> <key>");
        }

        CryptAnalyzer.Mode mode = checkCommandValidityAndConvert(args[0]);

        String path = args[1];
        checkFileExists(path);

        int key = checkIsIntegerAndConvert(args[2]);

        return new CryptParams(mode, path, key);
    }

    private static CryptAnalyzer.Mode checkCommandValidityAndConvert(String command) {
        try {
            return CryptAnalyzer.Mode.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid command. Use 'ENCRYPT' or 'DECRYPT'.");
        }
    }

    private static void checkFileExists(String filePath) {
        if (!new File(filePath).exists()) {
            throw new FileSystemNotFoundException("File not found: " + filePath);
        }
    }

    private static int checkIsIntegerAndConvert(String key) {
        try {
            return Integer.parseInt(key);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Key must be an integer.");
        }
    }
}
