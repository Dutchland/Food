package voedingswaarde;

import java.util.List;

public class Conditions {
    public static void notBiggerThan(double value, double reference, String message) {
        if (value > reference) {
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

    public static <T> void notNullOrEmpty(List<T> list, String message) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
