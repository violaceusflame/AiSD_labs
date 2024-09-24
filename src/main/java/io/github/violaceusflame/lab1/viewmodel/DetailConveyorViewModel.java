package io.github.violaceusflame.lab1.viewmodel;

import io.github.violaceusflame.lab1.observer.Observable;
import io.github.violaceusflame.lab1.observer.Observer;
import io.github.violaceusflame.lab1.detailconveyor.DetailConveyor;

import java.util.ArrayList;
import java.util.List;

public class DetailConveyorViewModel implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final DetailConveyor detailConveyor;
    private String time;
    private String currentQueueState;
    private String log;

    public DetailConveyorViewModel(DetailConveyor detailConveyor) {
        this.detailConveyor = detailConveyor;
        detailConveyor.addObserver(this);
    }

    public void add(String code, int time) {
        detailConveyor.add(code, time);
    }

    public void remove() {
        detailConveyor.remove();
    }

    public void nextTurn() {
        detailConveyor.nextTurn();
    }

    public void exit() {
        detailConveyor.stop();
        System.exit(0);
    }

    @Override
    public void onUpdate() {
        time = String.valueOf(detailConveyor.getCurrentTime());
        currentQueueState = detailConveyor.getQueue();
        log = getLastLogMessage();
        notifyObservers();
    }

    private String getLastLogMessage() {
        List<String> logs = detailConveyor.getLogs();
        int lastLogMessageIndex = logs.size() - 1;
        return (logs.isEmpty()) ? "" : logs.get(lastLogMessageIndex);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::onUpdate);
    }

    public String getTime() {
        return time;
    }

    public String getCurrentQueueState() {
        return currentQueueState;
    }

    public String getLog() {
        return log;
    }
}
