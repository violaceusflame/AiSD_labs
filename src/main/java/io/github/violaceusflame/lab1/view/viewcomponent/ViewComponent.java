package io.github.violaceusflame.lab1.view.viewcomponent;

import io.github.violaceusflame.lab1.view.View;

import java.util.function.Supplier;

public interface ViewComponent extends View {
    void setOnUpdateSupplier(Supplier<String> supplier);
}
