package org.virtual.thread.benchmark.utilities;

public class ConsoleUtils {

    // Source: https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private ConsoleUtils() {}

    public static void printCurrentRunningTest(String testName) {
        System.out.print(ANSI_CYAN + "TEST: " + ANSI_RESET + testName);
    }

    public static void printTestDone() {
        System.out.print("\t" + ANSI_GREEN + "FINISHED" + ANSI_RESET);
        System.out.println();
    }

    public static void printGreenBackground(String text) {
        System.out.println(ANSI_BLACK + ANSI_GREEN_BACKGROUND + " " + text + " " + ANSI_RESET);
    }

    public static void printCyanTextNewLine(String text) {
        System.out.println(ANSI_CYAN + text + ANSI_RESET);
    }

    public static void printGreenTextSameLine(String text) {
        System.out.print(ANSI_GREEN + text + ANSI_RESET);
    }

    public static void printYellowTextSameLine(String text) {
        System.out.print(ANSI_YELLOW + text + ANSI_RESET);
    }
}
