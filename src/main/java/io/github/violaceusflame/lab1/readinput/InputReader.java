package io.github.violaceusflame.lab1.readinput;

import java.util.Scanner;

public class InputReader {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readInput() {
        return scanner.nextLine();
    }
}
