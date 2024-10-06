package io.github.violaceusflame.lab1.detailconveyor;

public class Timer {
    private static final int START_TIME = 0;

    private int time = START_TIME;

    public void increment() {
        ++time;
    }

    public int getCurrentTime() {
        return time;
    }
}
