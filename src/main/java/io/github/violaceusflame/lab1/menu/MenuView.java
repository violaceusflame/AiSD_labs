package io.github.violaceusflame.lab1.menu;

import io.github.violaceusflame.lab1.readinput.InputReader;
import io.github.violaceusflame.lab1.view.View;

import java.util.ArrayList;
import java.util.List;

import static io.github.violaceusflame.lab1.util.Utils.*;

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

    private static final char INVALID_CHARACTER = Character.MIN_VALUE;

    private static final String TEMPLATE = "[%d] %s";
    private static final int START_COMMAND_ID = 1;

    private final List<Command> menuCommands = new ArrayList<>();
    private int commandIdSequence = START_COMMAND_ID;

    public void startActionByInput(char input) {
        int parsedCommandId = Integer.parseInt(String.valueOf(input));
        int commandIndex = parsedCommandId - 1;
        Action action = menuCommands.get(commandIndex).action;
        action.execute();
    }

    public void addAction(String title, Action action) {
        menuCommands.add(new Command(commandIdSequence++, title, action));
    }

    @Override
    public void show() {
        System.out.print(getPresentation());
        char ch = getInput();
        if (ch == INVALID_CHARACTER) {
            return;
        }
        startActionByInput(ch);
    }

    private char getInput() {
        String input = InputReader.readInput();
        if (!isValid(input)) {
            System.out.println("Некорректный ввод");
            return INVALID_CHARACTER;
        }
        return input.charAt(0);
    }

    private boolean isValid(String input) {
        return input.length() == 1 && isInteger(input.charAt(0)) && isInMenuCommandsRange(input.charAt(0));
    }

    private boolean isInMenuCommandsRange(char c) {
        int parsed = Integer.parseInt(String.valueOf(c));
        return 1 <= parsed && parsed <= menuCommands.size();
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
