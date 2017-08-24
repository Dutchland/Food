package com.voeding.utils;

import java.util.Collection;

public class Conditions {
    public static void notBiggerThan(Number value, Number reference, String message) {
        if (value.doubleValue() > reference.doubleValue()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNullOrEmpty(String value, String message) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isPositive(Number value, String message) {
        if (value.doubleValue() < 0d) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void notNullOrEmpty(Collection<T> list, String message) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
