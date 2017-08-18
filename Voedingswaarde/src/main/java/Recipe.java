import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {
    private final String name;
    List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        Conditions.notNullOrEmpty(name, "Recipe name cannot be empty");
        Conditions.notNullOrEmpty(ingredients, "Ingredients cannot be empty for recipe: " + name);

        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
    }

    public Recipe(String name, Ingredient... ingredients) {
        this(name, Arrays.asList(ingredients));
    }

    public double calories() {
        return ingredients.stream()
                .mapToDouble(Ingredient::calories)
                .sum();
    }

    public double caloriesForAmount(Amount requestedAmount) {
        double factor = amountFactor(requestedAmount);
        return this.calories() * factor;
    }

    public Amount getAmount() {
        Amount amount = ingredients.stream()
                .map(Ingredient::getAmount)
                .reduce(Amount.ZERO(), Amount::add);

        return amount;
    }

    public Amount getMacroAmount(MacroType type) {
        Amount amount = ingredients.stream()
                .map(i -> i.getMacroAmount(type))
                .reduce(Amount.ZERO(), Amount::add);

        return amount;
    }

    public Amount getMacroAmountForAmount(MacroType type, Amount requestedAmount) {
        double factor = amountFactor(requestedAmount);
        return getMacroAmount(type).multiply(factor);
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

    private double amountFactor(Amount requestedAmount) {
        return (double)requestedAmount.milliGrams() / (double) getAmount().milliGrams();
    }
}
