package io.github.violaceusflame.lab1.menu;

import io.github.violaceusflame.lab1.readinput.InputReader;
import io.github.violaceusflame.lab1.renderer.Renderer;
import io.github.violaceusflame.lab1.view.View;

import java.util.Scanner;
import java.util.function.BiConsumer;

public class AddDetailMenuView implements View {
    private static final String HEADER = "[РЕЖИМ ДОБАВЛЕНИЯ ДЕТАЛИ]";
    private static final String CODE_TYPE_MESSAGE = "Введите код детали: ";
    private static final String TIME_TYPE_MESSAGE = "Введите время обработки детали: ";
    private static final Scanner scanner = new Scanner(System.in);

    private final Renderer renderer;
    private BiConsumer<String, Integer> action;

    public AddDetailMenuView(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public synchronized void show() {
        System.out.println(HEADER);
        System.out.println(CODE_TYPE_MESSAGE);
        String code = InputReader.readInput();
        System.out.println(TIME_TYPE_MESSAGE);
        String time = InputReader.readInput();
        action.accept(code, Integer.parseInt(time));
    }

    public void addAction(BiConsumer<String, Integer> action) {
        this.action = action;
    }

    private synchronized String readInput() {
        return scanner.nextLine();
    }
}
