package io.github.violaceusflame.lab1.view.viewcomponent;

import java.util.function.Supplier;

public class QueueStateViewComponent implements ViewComponent {
    private static final String TEMPLATE = """
            Текущая очередь:
            """;

    private Supplier<String> supplier;

    @Override
    public void show() {
        String onUpdateData = supplier.get();
        System.out.println(TEMPLATE + onUpdateData);
    }

    @Override
    public void setOnUpdateSupplier(Supplier<String> supplier) {
        this.supplier = supplier;
    }
}
