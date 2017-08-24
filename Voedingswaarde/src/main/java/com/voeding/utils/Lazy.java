package com.voeding.utils;

import java.util.function.Supplier;

public class Lazy<T> {
    private final Supplier<T> provider;
    private T value;

    public Lazy(Supplier<T> provider) {
        this.provider = provider;
    }

    public synchronized T value() {
        if (value == null) {
            value = this.provider.get();
        }

        return value;
    }
}
