package com.doublerobot;

public class MacSpecific {

    public static boolean isMac() {
        return System.getProperty("os.name").contains("Mac");
    }


}
