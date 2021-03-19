package com.grupp4.radioproject.utils;

public class PrintUtils {

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
    public static void printlnc(String str, ConsoleColor color) {
        System.out.println(color + str + ConsoleColor.RESET);
    }

}
