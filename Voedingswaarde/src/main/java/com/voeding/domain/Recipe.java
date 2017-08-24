package com.voeding.domain;

import com.voeding.utils.Conditions;
import com.voeding.utils.NonEmptyList;

import java.math.BigDecimal;

public class Recipe {
    private final String name;
    private final NonEmptyList<Ingredient> ingredients;
    private final RecipeId id;

    public Recipe(RecipeId id, String name, NonEmptyList<Ingredient> ingredients) {
        Conditions.notNullOrEmpty(name, "Recipe name cannot be empty");
        Conditions.notNull(ingredients, "Ingredients cannot be empty for recipe: " + name);
        Conditions.notNull(id, "Recipe: id cannot be empty");

        this.id = id;
        this.name = name;
        this.ingredients = new NonEmptyList<>(ingredients);
    }

    public Recipe(RecipeId id, String name, Ingredient... ingredients) {
        this(id, name, new NonEmptyList<>(ingredients));
    }

    public Calories calories() {
        return ingredients.stream()
                .map(Ingredient::calories)
                .reduce(Calories.ZERO(), Calories::add);
    }

    public Calories caloriesForAmount(Amount requestedAmount) {
        Calories caloriesForWholeRecipe = calories();
        BigDecimal amountFactor = amountFactor(requestedAmount);

        return caloriesForWholeRecipe.multiply(amountFactor);
    }

    public Amount getAmount() {
        Amount amount = ingredients.stream()
                .map(Ingredient::getAmount)
                .reduce(Amount.inGrams(0), Amount::add);

        return amount;
    }

    public Amount getMacroAmount(MacroType type) {
        Amount amount = ingredients.stream()
                .map(i -> i.getAmount(type))
                .reduce(Amount.inGrams(0), Amount::add);

        return amount;
    }

    public Amount getMacroAmountForAmount(MacroType type, Amount requestedAmount) {
        Amount amountForWholeRecipe = getMacroAmount(type);
        BigDecimal amountFactor = amountFactor(requestedAmount);

        return amountForWholeRecipe.multiply(amountFactor);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Name: " + this.name + "\n");

        ingredients.forEach(i -> builder
                .append(i)
                .append("\n")
                .append("\n"));

        return builder.toString();
    }

    public RecipeId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NonEmptyList<Ingredient> getIngredients() {
        return new NonEmptyList<>(this.ingredients);
    }

    private BigDecimal amountFactor(Amount requestedAmount) {
        double factor = (double)requestedAmount.milliGrams() / (double) getAmount().milliGrams();
        return BigDecimal.valueOf(factor);
    }
}
