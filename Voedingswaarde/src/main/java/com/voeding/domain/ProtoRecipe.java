package com.voeding.domain;

import com.voeding.utils.Conditions;

import java.util.Map;

public class ProtoRecipe {
    private final String name;
    private final Map<ProductId, Amount> ingredients;

    public ProtoRecipe(String name, Map<ProductId, Amount> ingredients) {
        Conditions.notNullOrEmpty(name, "Recipe name cannot be empty");
        Conditions.notNullOrEmpty(ingredients.entrySet(), "Ingredients cannot be empty for recipe: " + name);

        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public Map<ProductId, Amount> getIngredients() {
        return ingredients;
    }
}
