package io.github.violaceusflame.lab1.view.viewcomponent;

import java.util.function.Supplier;

public class LoggerViewComponent implements ViewComponent {
    private Supplier<String> supplier;

    @Override
    public void show() {
        System.out.println(supplier.get());
    }

    @Override
    public void setOnUpdateSupplier(Supplier<String> supplier) {
        this.supplier = supplier;
    }
}
