package com.voeding.domain.valueobjects;

import com.voeding.utils.Conditions;

import java.math.BigDecimal;

public class Amount {

    private static final int MILLIGRAM_PER_GRAM = 1000;

    private final long amountInMilliGrams;

    private Amount(long amountInMilliGrams) {
        Conditions.isPositive(amountInMilliGrams, "Amount must be positive");
        this.amountInMilliGrams = amountInMilliGrams;
    }

    public static Amount inMilligrams(long amountInMilliGrams) {
        return new Amount(amountInMilliGrams);
    }

    public static Amount inGrams(int amountInGrams) {
        return new Amount(amountInGrams * MILLIGRAM_PER_GRAM);
    }

    public Amount add(Amount otherAmount) {
        return new Amount(this.amountInMilliGrams + otherAmount.amountInMilliGrams);
    }

    public Amount multiply(BigDecimal multiplier) {
        long amountInMilligrams = BigDecimal.valueOf(this.amountInMilliGrams)
                .multiply(multiplier)
                .longValue();

        return new Amount(amountInMilligrams);
    }

    public long milliGrams() {
        return amountInMilliGrams;
    }

    public static Amount HUNDRED_GRAMS() {
        return new Amount(100 * MILLIGRAM_PER_GRAM);
    }

    public static Amount ZERO() {
        return Amount.inGrams(0);
    }

    @Override
    public String toString() {
        boolean moreThan10Gram = this.amountInMilliGrams > (10* MILLIGRAM_PER_GRAM);
        boolean moreThanAGram = this.amountInMilliGrams > MILLIGRAM_PER_GRAM;

        if (moreThan10Gram) {
            return this.amountInMilliGrams / MILLIGRAM_PER_GRAM + " gram";
        } else if(moreThanAGram) {
            return (double) this.amountInMilliGrams / (double) MILLIGRAM_PER_GRAM + " gram";
        } else {
            return this.amountInMilliGrams + " milligram";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount = (Amount) o;

        return amountInMilliGrams == amount.amountInMilliGrams;
    }

    @Override
    public int hashCode() {
        return (int) (amountInMilliGrams ^ (amountInMilliGrams >>> 32));
    }
}
