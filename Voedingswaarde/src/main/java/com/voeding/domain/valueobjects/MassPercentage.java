package com.voeding.domain.valueobjects;

import com.voeding.utils.Conditions;
import java.math.BigDecimal;

public class MassPercentage {
    private static final BigDecimal MAX_VALUE = BigDecimal.ONE;
    private final BigDecimal percentage;

    public MassPercentage(BigDecimal percentage) {
        Conditions.notBiggerThan(percentage, MAX_VALUE, "MassPercentage cannot be bigger than " + MAX_VALUE + ": " + percentage);
        Conditions.isPositive(percentage, "MassPercentage cannot be negative: " + percentage);
        this.percentage = percentage;
    }

    public BigDecimal value() {
        return this.percentage;
    }

    public static MassPercentage HUNDRED_PROCENT() {
        return new MassPercentage(MAX_VALUE);
    }

    public MassPercentage add(MassPercentage otherPercentage) {
        return new MassPercentage(this.value().add(otherPercentage.value()));
    }

    public static MassPercentage ZERO() {
        return new MassPercentage(BigDecimal.ZERO);
    }

    @Override
    public String toString() {
        BigDecimal percent = percentage.multiply(BigDecimal.valueOf(100));
        return percent.setScale(1, BigDecimal.ROUND_HALF_UP) + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MassPercentage that = (MassPercentage) o;

        return percentage.equals(that.percentage);
    }

    @Override
    public int hashCode() {
        return percentage.hashCode();
    }
}
