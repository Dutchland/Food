package com.voeding.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class NonEmptyList<T> extends ArrayList<T> {

    public NonEmptyList(Collection<T> collection) {
        super(collection);
        Conditions.notNullOrEmpty(collection, "Collection cannot be empty");
    }

    public NonEmptyList(T... collection) {
        this(Arrays.asList(collection));
    }

    @Override
    public boolean remove(Object o) {
        if(this.size() == 1) {
            throw new RuntimeException("List cannot be made empty");
        }

        return super.remove(o);
    }
}
