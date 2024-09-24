package io.github.violaceusflame.lab1.observer;

public interface Observable {
    void addObserver(Observer o);
    void notifyObservers();
}
