package io.github.violaceusflame.lab1.detailconveyor;

import io.github.violaceusflame.lab1.observer.Observable;
import io.github.violaceusflame.lab1.observer.Observer;
import io.github.violaceusflame.lab1.queue.Queue;
import io.github.violaceusflame.lab1.util.Logger;
import io.github.violaceusflame.lab1.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class DetailConveyor implements Observable, Observer {
    private enum LogMessageTemplate {
        ADDED("Добавлена деталь с кодом '%s'"),
        PROCESSED("[Время: %d] Обработана деталь с кодом '%s'"),
        CANCELLED("[Время: %d] Выполнен отказ от установки детали с кодом '%s'");

        public final String text;

        LogMessageTemplate(String text) {
            this.text = text;
        }
    }

    private static final String FULL_QUEUE = "Очередь заполнена";
    private static final String EMPTY_QUEUE = "Очередь пуста";
    private static final int TIME_TO_WAIT_NEW_QUEUE_ITEMS = 500;

    private final Queue<Detail> detailQueue;
    private final Timer timer;
    private final Logger logger = new Logger();
    private final List<Observer> observers = new ArrayList<>();
    private boolean isRunning;

    public DetailConveyor(Queue<Detail> detailQueue) {
        this.detailQueue = detailQueue;
        this.timer = new Timer();
        this.timer.addObserver(this);
    }

    public void add(String code, int time) {
        if (detailQueue.isFull()) {
            logAndNotifyObservers(FULL_QUEUE);
            return;
        }

        Detail detail = new Detail(code, time);
        detailQueue.enqueue(detail);
        String logMessage = LogMessageTemplate.ADDED.text.formatted(detail.code());
        logAndNotifyObservers(logMessage);
    }

    public Detail remove() {
        if (detailQueue.isEmpty()) {
            logAndNotifyObservers(EMPTY_QUEUE);
            return null;
        }

        Detail dequeued = detailQueue.dequeue();
        // TODO надо ли?
//        notifyObservers();
        return dequeued;
    }

    public void start() {
        isRunning = true;
        startTimer();

        while (isRunning) {
            while (!detailQueue.isEmpty()) {
                processDetail();
            }
            Utils.sleep(TIME_TO_WAIT_NEW_QUEUE_ITEMS);
        }
    }

    private void startTimer() {
        Thread timerThread = new Thread(timer::start, "timer thread");
        timerThread.start();
    }

    public void nextTurn() {
        timer.increment();
    }

    public void stop() {
        timer.stop();
        isRunning = false;
    }

    // TODO: отрефакторить метод по Фаулеру
    public void processDetail() {
        Detail detail = detailQueue.front();
        int timeToEnd = timer.getCurrentTime() + detail.time();
        while (timer.getCurrentTime() < timeToEnd) {
            if (detailQueue.front() != detail) {
                String logMessage = LogMessageTemplate.CANCELLED.text.formatted(timer.getCurrentTime(), detail.code());
                logAndNotifyObservers(logMessage);
                return;
            }
            Thread.yield();
        }
        String logMessage = LogMessageTemplate.PROCESSED.text.formatted(timeToEnd, detailQueue.dequeue().code());
        logAndNotifyObservers(logMessage);
    }

    private void logAndNotifyObservers(String logMessage) {
        logger.log(logMessage);
        notifyObservers();
    }

    public int getCurrentTime() {
        return timer.getCurrentTime();
    }

    public List<String> getLogs() {
        return logger.getLogs();
    }

    public String getQueue() {
        return detailQueue.toString();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.onUpdate();
        }
    }

    @Override
    public void onUpdate() {
        notifyObservers();
    }
}
