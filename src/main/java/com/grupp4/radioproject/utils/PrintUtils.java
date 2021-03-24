package com.grupp4.radioproject.utils;

import static com.grupp4.radioproject.utils.ConsoleColor.*;

public class PrintUtils {

    private static final ConsoleColor ERROR_COLOR = WHITE;
    private static final ConsoleColor ERROR_BG_COLOR = RED_BG;
    private static final ConsoleColor INFO_COLOR = GREEN;
    private static final ConsoleColor INFO_BG_COLOR = WHITE_BG;
    private static final ConsoleColor DEBUG_COLOR = GRAY;
    private static final ConsoleColor DEBUG_BG_COLOR = YELLOW_BG;

    static {
        printDebug("This is a debug message!");
        printError("This is an error message!");
        printInfo("This is  an info message!");
    }


    /**
     * <p>
     *     Prints a colored line.
     *     import this so that you don't have to write PrintUtils.printlnc. printlnc will be enough.
     * </p>
     * <p>
     *     <code>import static com.grupp4.radioproject.utils.PrintUtils.printlnc;</code>
     * </p>
     * <p>
     *     <i>Usage:</i><br>
     *     <code> printlnc("This text is blue", ConsoleColor.BLUE);</code>
     * </p>
     * <p>
     *     @param color Which color to use. (use ConsoleColor.[color])
     *     @param str String to print.
     * </p>
     */
    public static void printColoredLine(String str, ConsoleColor color) {
        System.out.println(color + str + RESET);
    }

    public static void printError(String str) {
        System.out.println("" + ERROR_BG_COLOR + ERROR_COLOR + " ERROR " + RESET + RED + " " + str + RESET);
    }

    public static void printInfo(String str) {
        System.out.println("" + GREEN_BG + WHITE + " INFO " + RESET + GREEN + " " + str);
    }

    public static void printDebug(String str) {
        System.out.println("" + DEBUG_BG_COLOR + DEBUG_COLOR + " DEBUG " + RESET + YELLOW + " "  + str);
    }

}
