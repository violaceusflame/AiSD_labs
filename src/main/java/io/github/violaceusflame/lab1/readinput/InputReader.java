package io.github.violaceusflame.lab1.readinput;

import java.util.Scanner;

// TODO надо ли?
public class InputReader {
    private static final Scanner scanner = new Scanner(System.in);

    public static synchronized String readInput() {
        return scanner.nextLine();
    }
}
