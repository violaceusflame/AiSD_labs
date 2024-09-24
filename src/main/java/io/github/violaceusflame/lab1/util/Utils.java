package io.github.violaceusflame.lab1.util;

import java.util.Scanner;

public class Utils {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
