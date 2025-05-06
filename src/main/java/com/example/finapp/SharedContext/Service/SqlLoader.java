package com.example.finapp.SharedContext.Service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * SqlLoader is responsible for loading SQL files from the classpath and returning their contents as a String.
 * This is useful for reading SQL queries stored in external files instead of hardcoding them into the codebase.
 */
@Component
public class SqlLoader {

    /**
     * Loads the SQL file from the classpath and returns its contents as a String.
     *
     * @param path The path to the SQL file within the classpath (e.g., "queries/transactions/selectAll.sql").
     * @return The contents of the SQL file as a String.
     * @throws IllegalArgumentException If the specified SQL file is not found in the classpath.
     * @throws RuntimeException If an error occurs while reading the file.
     */
    public static String load(String path) {
        try (InputStream is = SqlLoader.class.getClassLoader().getResourceAsStream(path)) {
            // Check if the file was found
            if (is == null) {
                throw new IllegalArgumentException("SQL file not found: " + path);
            }

            // Read the content of the file and convert it to a String
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // Handle any IO exceptions and throw a RuntimeException
            throw new RuntimeException("Error reading SQL file: " + path, e);
        }
    }
}
