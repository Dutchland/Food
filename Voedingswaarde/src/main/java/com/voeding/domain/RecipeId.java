package com.voeding.domain;

import com.voeding.utils.Conditions;

public class RecipeId{
    private final long id;

    public RecipeId(long id) {
        Conditions.isPositive(id, "Not a valid com.voeding.domain.RecipeId: " + id);
        this.id = id;
    }

    public long value() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeId recipeId = (RecipeId) o;

        return id == recipeId.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
