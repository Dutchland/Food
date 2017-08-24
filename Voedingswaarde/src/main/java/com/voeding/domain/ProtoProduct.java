package com.voeding.domain;

import com.voeding.domain.valueobjects.MassPercentage;
import com.voeding.utils.Conditions;

import java.util.Map;

public class ProtoProduct {
    private final String name;
    private final Map<MacroType, MassPercentage> macros;

    public ProtoProduct(String name, Map<MacroType, MassPercentage> macros) {
        this.name = name;
        this.macros = macros;

        Conditions.notNullOrEmpty(name, "Product: ingredientname cannot be empty");
        Conditions.notBiggerThan(getMacro(MacroType.SATURATED_FAT).value(), getMacro(MacroType.FAT).value(), "Saturated fat cannot be bigger than total fat");
        Conditions.notBiggerThan(getMacro(MacroType.SUGAR).value(), getMacro(MacroType.CARBOHYDRATE).value(), "Sugar cannot be bigger than carbohydrates");
    }

    public MassPercentage getMacro(MacroType type) {
        return macros.entrySet().stream()
                .filter(entry -> entry.getKey().equals(type))
                .map(entry -> entry.getValue())
                .findFirst()
                .orElse(MassPercentage.ZERO());
    }

    public String getName() {
        return name;
    }

    public Map<MacroType, MassPercentage> getMacros() {
        return macros;
    }
}
