package io.github.violaceusflame.lab1.detailconveyor;

import io.github.violaceusflame.lab1.observer.Observable;
import io.github.violaceusflame.lab1.observer.Observer;
import io.github.violaceusflame.lab1.queue.Queue;
import io.github.violaceusflame.lab1.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class DetailConveyor implements Observable {
    private enum LogMessageTemplate {
        ADDED("[Время: %d] Добавлена деталь с кодом '%s'"),
        PROCESSED("[Время: %d] Обработана деталь с кодом '%s'"),
        CANCELLED("[Время: %d] Выполнен отказ от установки детали с кодом '%s'");

        public final String text;

        LogMessageTemplate(String text) {
            this.text = text;
        }
    }

    private static final String FULL_QUEUE = "Очередь заполнена";
    private static final String EMPTY_QUEUE = "Очередь пуста";
    private static final String RESET_QUEUE = "Очередь очищена";
    private static final int UNSPECIFIED_DEADLINE = 0;

    private final Queue<Detail> detailQueue;
    private final Timer timer = new Timer();
    private final Logger logger = new Logger();
    private final List<Observer> observers = new ArrayList<>();
    private int currentProcessDetailDeadline = UNSPECIFIED_DEADLINE;

    public DetailConveyor(Queue<Detail> detailQueue) {
        this.detailQueue = detailQueue;
    }

    public void add(String code, int time) {
        if (detailQueue.isFull()) {
            logAndNotifyObservers(FULL_QUEUE);
            return;
        }

        Detail detail = new Detail(code, time);
        detailQueue.enqueue(detail);
        String logMessage = LogMessageTemplate.ADDED.text.formatted(timer.getCurrentTime(), detail.code());
        logAndNotifyObservers(logMessage);
    }

    public Detail remove() {
        if (detailQueue.isEmpty()) {
            logAndNotifyObservers(EMPTY_QUEUE);
            return null;
        }

        Detail dequeued = detailQueue.dequeue();
        String logMessage = LogMessageTemplate.CANCELLED.text.formatted(timer.getCurrentTime(), dequeued.code());
        resetProcessDetailDeadline();
        logAndNotifyObservers(logMessage);
        return dequeued;
    }

    public void reset() {
        detailQueue.reset();
        resetProcessDetailDeadline();
        logAndNotifyObservers(RESET_QUEUE);
    }

    private void resetProcessDetailDeadline() {
        currentProcessDetailDeadline = UNSPECIFIED_DEADLINE;
    }

    public void start() {
        notifyObservers();
    }

    public void nextTurn() {
        timer.increment();
        processDetail();
        notifyObservers();
    }

    // TODO: отрефакторить метод по Фаулеру
    public synchronized void processDetail() {
        if (detailQueue.isEmpty()) {
            return;
        }

        if (currentProcessDetailDeadline == UNSPECIFIED_DEADLINE) {
            Detail currentDetail = detailQueue.front();
            // FIXME костыль
            currentProcessDetailDeadline = timer.getCurrentTime() + currentDetail.time() - 1;
        }
        if (timer.getCurrentTime() == currentProcessDetailDeadline) {
            Detail dequeued = detailQueue.dequeue();
            String logMessage = LogMessageTemplate.PROCESSED.text.formatted(timer.getCurrentTime(), dequeued.code());
            resetProcessDetailDeadline();
            logAndNotifyObservers(logMessage);
        }
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
}
