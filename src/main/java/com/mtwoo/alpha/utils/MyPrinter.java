package com.mtwoo.alpha.utils;

public class MyPrinter {
    public static final String RESET = "\033[0m";
    public static final String ERROR = "\033[0;31m My Error";
    public static final String WARN = "\033[0;33m My WARN";
    public static final String INFO = "\033[0;32m My INFO";
    public static final String DEBUG = "\033[0;34m My DEBUG";

    private static int breakPointCounter = 0;

    public static void print(String msg){
        print(msg, DEBUG);
    }

    public static void breakPoint(){
        print("Break Point " + breakPointCounter++ + " **************************************************");
    }

    public static void breakPoint(String mark){
        print("Break Point " + mark + "(" + breakPointCounter++ + ") **************************************************");
    }

    public static void print(String msg, String type){
        System.out.println(type + RESET + ": " + msg);
    }

    public static void printInfo(String msg){
        print(msg, INFO);
    }

    public static void printError(String msg){
        print(msg, ERROR);
    }

    public  static void printWarn(String msg){
        print(msg, WARN);
    }
}
