package io.github.violaceusflame.lab1.renderer;

import io.github.violaceusflame.lab1.view.View;

import java.util.ArrayList;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    private enum AnsiEscapeCode {
        CLEAR_CONSOLE("\u001b\143"),
        HIDE_CURSOR("\u001b[?25l"),
        SHOW_CURSOR("\u001b[?25h");

        public final String code;

        AnsiEscapeCode(String code) {
            this.code = code;
        }
    }

    private final List<View> views = new ArrayList<>();
    private boolean isPaused = false;

    @Override
    public synchronized void render() {
        if (!isPaused) {
            clearConsole();
            hideCursor();
            renderViews();
            showCursor();
        }
    }

    private void renderViews() {
        for (View view : views) {
            view.show();
        }
    }

    @Override
    public void addView(View view) {
        views.add(view);
    }

    @Override
    public void pauseRender() {
        isPaused = true;
    }

    @Override
    public void resumeRender() {
        isPaused = false;
    }

    private void showCursor() {
        System.out.print(AnsiEscapeCode.SHOW_CURSOR.code);
    }

    private void hideCursor() {
        System.out.print(AnsiEscapeCode.HIDE_CURSOR.code);
    }

    private void clearConsole() {
        System.out.print(AnsiEscapeCode.CLEAR_CONSOLE.code);
    }
}
