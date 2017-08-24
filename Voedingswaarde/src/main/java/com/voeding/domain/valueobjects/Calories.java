package com.voeding.domain.valueobjects;

import java.math.BigDecimal;

public class Calories {

    private final BigDecimal value;

    public Calories(BigDecimal value) {
        this.value = value;
    }

     public BigDecimal value() {
        return value;
    }

    public static Calories ZERO() {
        return new Calories(BigDecimal.ZERO);
    }

    public Calories add(Calories calories) {
        return new Calories(this.value.add(calories.value));
    }

    public Calories multiply(BigDecimal multiplier) {
        return new Calories(value.multiply(multiplier));
    }

    @Override
    public String toString() {
        return value.setScale(1, BigDecimal.ROUND_HALF_UP) + " Calories";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calories calories = (Calories) o;

        return value.equals(calories.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
