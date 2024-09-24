package io.github.violaceusflame.lab1.menu;

import io.github.violaceusflame.lab1.readinput.InputReader;
import io.github.violaceusflame.lab1.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuView implements View {
    private static class Command {
        public int commandId;
        public String title;
        public Action action;

        public Command(int commandId, String title, Action action) {
            this.commandId = commandId;
            this.title = title;
            this.action = action;
        }
    }

    @FunctionalInterface
    public interface Action {
        void execute();
    }

    private static final String TEMPLATE = "[%d] %s";
    private static final int START_COMMAND_ID = 1;
    private static final Scanner scanner = new Scanner(System.in);

    private final List<Command> menuCommands = new ArrayList<>();
    private int commandIdSequence = START_COMMAND_ID;

    public MenuView() {
        new Thread(() -> {
            while (true) {
                this.getInput();
            }
        }, "input thread").start();
    }

    public String getInput() {
        while (true) {
            char ch = InputReader.readInput().charAt(0);
            if (isInteger(ch)) {
                int parsedCommandId = Integer.parseInt(String.valueOf(ch));
                int commandIndex = parsedCommandId - 1;
                Action action = menuCommands.get(commandIndex).action;
                action.execute();
            }
            return String.valueOf(ch);
        }
    }

    private synchronized String readInput() {
        return scanner.nextLine();
    }

    private boolean isInteger(char ch) {
        try {
            Integer.parseInt(String.valueOf(ch));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void addAction(String title, Action action) {
        menuCommands.add(new Command(commandIdSequence++, title, action));
    }

    @Override
    public void show() {
        System.out.print(getPresentation());
//        getInput();
//        new Thread(this::getInput, "input thread").start();
    }

    public String getPresentation() {
        StringBuilder stringBuilder = new StringBuilder("Меню:\n");
        for (Command command : menuCommands) {
            stringBuilder.append(getFormattedCommandString(command)).append("\n");
        }
        stringBuilder.append(">>> ");
        return stringBuilder.toString();
    }

    private String getFormattedCommandString(Command command) {
        return TEMPLATE.formatted(command.commandId, command.title);
    }
}
