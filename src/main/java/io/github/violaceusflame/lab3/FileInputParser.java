package io.github.violaceusflame.lab3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInputParser {
    private final String filePath;
    private final List<String> result = new ArrayList<>();

    public FileInputParser(String filePath) {
        this.filePath = filePath;
    }

    public void readFile() {
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getResult() {
        readFile();
        return result;
    }
}
