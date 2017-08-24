package com.voeding.domain;

import com.voeding.utils.Conditions;

import java.math.BigDecimal;

public class Ingredient {

    private final Product product;
    private final Amount amount;

    public Ingredient(Product product, Amount amount) {
        Conditions.notNull(product, "Ingredient: product cannot be empty");
        Conditions.notNull(amount, "Ingredient: amount cannot be empty");
        this.product = product;
        this.amount = amount;
    }

    public Calories calories() {
        double carbohydrates = getAmount(MacroType.CARBOHYDRATE).milliGrams() / 1000d * MacroType.CARBOHYDRATE.getCaloriesPerGram();
        double fat = getAmount(MacroType.FAT).milliGrams() / 1000d * MacroType.FAT.getCaloriesPerGram();
        double protein = getAmount(MacroType.PROTEIN).milliGrams() / 1000d * MacroType.PROTEIN.getCaloriesPerGram();

        return new Calories(BigDecimal.valueOf(carbohydrates + fat + protein));
    }

    public Amount getAmount(MacroType type) {
        MassPercentage percentage = product.getMacro(type);
        return amount.multiply(percentage.value());
    }

    public Amount getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return product.toString() + " " + amount.toString();
    }
}
