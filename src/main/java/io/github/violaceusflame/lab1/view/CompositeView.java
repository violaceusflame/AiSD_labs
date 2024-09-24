package io.github.violaceusflame.lab1.view;

import io.github.violaceusflame.lab1.observer.Observer;
import io.github.violaceusflame.lab1.view.viewcomponent.ViewComponent;
import io.github.violaceusflame.lab1.viewmodel.DetailConveyorViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompositeView implements View, Observer {
    private final List<ViewComponent> viewComponents = new ArrayList<>();
    private final DetailConveyorViewModel viewModel;

    public CompositeView(DetailConveyorViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addObserver(this);
    }

    public void addComponent(ViewComponent component) {
        viewComponents.add(component);
    }

    @Override
    public void show() {
        for (ViewComponent component : viewComponents) {
            component.show();
        }
    }

    @Override
    public void onUpdate() {
        show();
    }
}
