package io.github.violaceusflame.lab1.detailconveyor;

import io.github.violaceusflame.lab1.observer.Observable;
import io.github.violaceusflame.lab1.observer.Observer;
import io.github.violaceusflame.lab1.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Timer implements Observable {
    private static final int MILLIS_PER_MODEL_SECOND = 800;

    private final List<Observer> observers = new ArrayList<>();
    private int time;
    private boolean isRunning;

    public void start() {
        isRunning = true;

        while (isRunning) {
            Utils.sleep(MILLIS_PER_MODEL_SECOND);
            increment();
            notifyObservers();
        }
    }

    public void increment() {
        ++time;
    }

    public void stop() {
        isRunning = false;
    }

    public int getCurrentTime() {
        return time;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::onUpdate);
    }
}
