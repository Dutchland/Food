package com.voeding.domain;

import com.voeding.utils.Conditions;

import java.util.Arrays;
import java.util.Map;

public class Product {
    private final String name;
    private final ProductId id;
    private final Map<MacroType, MassPercentage> macros;

    public Product(ProductId id, String name, Map<MacroType, MassPercentage> macros) {
        this.name = name;
        this.macros = macros;
        this.id = id;

        Conditions.notNullOrEmpty(name, "Product: ingredientname cannot be empty");
        Conditions.notNull(id, "Product: productId cannot be empty : " + name);
        Conditions.notBiggerThan(getMacro(MacroType.SATURATED_FAT).value(), getMacro(MacroType.FAT).value(), "Saturated fat cannot be bigger than total fat");
        Conditions.notBiggerThan(getMacro(MacroType.SUGAR).value(), getMacro(MacroType.CARBOHYDRATE).value(), "Sugar cannot be bigger than carbohydrates");
    }

    public MassPercentage getMacro(MacroType type) {
        return macros.entrySet().stream()
                .filter(entry -> entry.getKey().equals(type))
                .map(entry -> entry.getValue())
                .findFirst()
                .orElse(MassPercentage.zero());
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Product: " + this.name + " ");

        Arrays.stream(MacroType.values())
                .forEach(mt -> builder
                        .append(mt.toString() + " ")
                        .append(getMacro(mt).toString() + " "));

        return builder.toString();
    }

    public ProductId getId() {
        return id;
    }
}
