package io.github.violaceusflame.lab1.util;

public class Utils {
    public static boolean isInteger(char ch) {
        try {
            Integer.parseInt(String.valueOf(ch));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
