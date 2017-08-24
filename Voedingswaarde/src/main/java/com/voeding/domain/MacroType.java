package com.voeding.domain;

public enum MacroType {
    CARBOHYDRATE("Carbohydrate", 4),
    SUGAR("Sugar", 4),
    PROTEIN("Protein", 4),
    FAT("Fat", 9),
    SATURATED_FAT("Saturated fat", 9);

    private final String name;
    private final int caloriesPerGram;

    MacroType(String name, int caloriesPerGram) {
        this.name = name;
        this.caloriesPerGram = caloriesPerGram;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getCaloriesPerGram() {
        return caloriesPerGram;
    }
}
