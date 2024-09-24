package io.github.violaceusflame.lab1.view;

import io.github.violaceusflame.lab1.observer.Observer;
import io.github.violaceusflame.lab1.renderer.Renderer;
import io.github.violaceusflame.lab1.viewmodel.DetailConveyorViewModel;

public class RenderableCompositeView extends CompositeView implements View, Observer {
    private final Renderer renderer;

    public RenderableCompositeView(DetailConveyorViewModel viewModel, Renderer renderer) {
        super(viewModel);
        this.renderer = renderer;
        this.renderer.addView(this);
    }

    @Override
    public void onUpdate() {
        renderer.render();
    }
}
