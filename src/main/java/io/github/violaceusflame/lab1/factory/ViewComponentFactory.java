package io.github.violaceusflame.lab1.factory;

import io.github.violaceusflame.lab1.view.viewcomponent.LoggerViewComponent;
import io.github.violaceusflame.lab1.view.viewcomponent.QueueStateViewComponent;
import io.github.violaceusflame.lab1.view.viewcomponent.TimerViewComponent;

import java.util.function.Supplier;

public class ViewComponentFactory {
    public TimerViewComponent createTimerView(Supplier<String> supplier) {
        TimerViewComponent timerViewComponent = new TimerViewComponent();
        timerViewComponent.setOnUpdateSupplier(supplier);
        return timerViewComponent;
    }

    public LoggerViewComponent createLoggerView(Supplier<String> supplier) {
        LoggerViewComponent loggerViewComponent = new LoggerViewComponent();
        loggerViewComponent.setOnUpdateSupplier(supplier);
        return loggerViewComponent;
    }

    public QueueStateViewComponent createQueueStateView(Supplier<String> supplier) {
        QueueStateViewComponent queueStateViewComponent = new QueueStateViewComponent();
        queueStateViewComponent.setOnUpdateSupplier(supplier);
        return queueStateViewComponent;
    }
}
