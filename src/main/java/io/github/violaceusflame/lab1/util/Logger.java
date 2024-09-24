package io.github.violaceusflame.lab1.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logger {
    private final List<String> logs = new ArrayList<>();

    public void log(String message) {
        logs.add(message);
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(logs);
    }
}
