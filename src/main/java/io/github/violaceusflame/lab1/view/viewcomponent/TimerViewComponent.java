package io.github.violaceusflame.lab1.view.viewcomponent;

import java.util.function.Supplier;

public class TimerViewComponent implements ViewComponent {
    private static final String TEMPLATE = "Текущее время: %s";

    private Supplier<String> supplier;

    @Override
    public void show() {
        System.out.println(getFormattedTemplate(String.valueOf(supplier.get())));
    }

    @Override
    public void setOnUpdateSupplier(Supplier<String> supplier) {
        this.supplier = supplier;
    }

    private String getFormattedTemplate(String data) {
        return TEMPLATE.formatted(data);
    }
}
