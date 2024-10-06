package io.github.violaceusflame.lab1;

import io.github.violaceusflame.lab1.detailconveyor.Detail;
import io.github.violaceusflame.lab1.detailconveyor.DetailConveyor;
import io.github.violaceusflame.lab1.factory.ViewComponentFactory;
import io.github.violaceusflame.lab1.menu.AddDetailMenuView;
import io.github.violaceusflame.lab1.menu.MenuView;
import io.github.violaceusflame.lab1.queue.Queue;
import io.github.violaceusflame.lab1.queue.LinkedListQueue;
import io.github.violaceusflame.lab1.renderer.ConsoleRenderer;
import io.github.violaceusflame.lab1.renderer.Renderer;
import io.github.violaceusflame.lab1.view.CompositeView;
import io.github.violaceusflame.lab1.view.RenderableCompositeView;
import io.github.violaceusflame.lab1.view.viewcomponent.LoggerViewComponent;
import io.github.violaceusflame.lab1.view.viewcomponent.QueueStateViewComponent;
import io.github.violaceusflame.lab1.view.viewcomponent.TimerViewComponent;
import io.github.violaceusflame.lab1.viewmodel.DetailConveyorViewModel;

public class MainLinkedListQueueConfiguration {
    public static void main(String[] args) {
        Queue<Detail> detailQueue = new LinkedListQueue<>();
        detailQueue.enqueue(new Detail("ABCD", 5));
        detailQueue.enqueue(new Detail("QWER", 3));
        detailQueue.enqueue(new Detail("OIHF", 10));
        detailQueue.enqueue(new Detail("ZXCV", 15));

        DetailConveyor detailConveyor = new DetailConveyor(detailQueue);
        DetailConveyorViewModel detailConveyorViewModel = new DetailConveyorViewModel(detailConveyor);

        ViewComponentFactory viewComponentFactory = new ViewComponentFactory();
        TimerViewComponent timerViewComponent = viewComponentFactory.createTimerView(detailConveyorViewModel::getTime);
        LoggerViewComponent loggerViewComponent = viewComponentFactory.createLoggerView(detailConveyorViewModel::getLog);
        QueueStateViewComponent queueStateViewComponent = viewComponentFactory.createQueueStateView(detailConveyorViewModel::getCurrentQueueState);

        Renderer renderer = new ConsoleRenderer();

        CompositeView compositeView = new RenderableCompositeView(detailConveyorViewModel, renderer);
        compositeView.addComponent(timerViewComponent);
        compositeView.addComponent(loggerViewComponent);
        compositeView.addComponent(queueStateViewComponent);

        Renderer addDetailMenuViewRenderer = new ConsoleRenderer();
        AddDetailMenuView addDetailMenuView = new AddDetailMenuView(addDetailMenuViewRenderer);
        addDetailMenuView.addAction(detailConveyorViewModel::add);
        addDetailMenuViewRenderer.addView(addDetailMenuView);

        MenuView menuView = new MenuView();
        menuView.addAction("Выйти", detailConveyorViewModel::exit);
        menuView.addAction("Добавить в очередь", () -> {
            renderer.pauseRender();
            addDetailMenuView.show();
            renderer.resumeRender();
            renderer.render();
        });
        menuView.addAction("Удалить из очереди", detailConveyorViewModel::remove);
        menuView.addAction("Перейти к следующему моменту", detailConveyorViewModel::nextTurn);
        menuView.addAction("Сброс очереди", detailConveyorViewModel::reset);

        renderer.addView(menuView);

        detailConveyor.start();
    }
}
