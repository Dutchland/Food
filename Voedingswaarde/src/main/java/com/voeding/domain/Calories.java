package com.voeding.domain;

import java.math.BigDecimal;

public class Calories {

    private final BigDecimal value;

    public Calories(BigDecimal value) {
        this.value = value;
    }

     public BigDecimal value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value.setScale(1, BigDecimal.ROUND_HALF_UP));
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
}
