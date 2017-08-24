package com.voeding.utils;

public class IdGenerator {
    public static long uniqueId() {
        return System.nanoTime();
    }
}
