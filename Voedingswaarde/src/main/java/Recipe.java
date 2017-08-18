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

    public Amount getAmount() {
        Amount amount = ingredients.stream()
                .map(Ingredient::getAmount)
                .reduce(Amount.inGrams(0), Amount::add);

        return amount;
    }

    public Amount getAmount(MacroType type) {
        Amount amount = ingredients.stream()
                .map(i -> i.getAmount(type))
                .reduce(Amount.inGrams(0), Amount::add);

        return amount;
    }

    public Amount getMacroAmountPerAmount(MacroType type, Amount requestedAmount) {
        Amount amount = ingredients.stream()
                .map(i -> i.getAmount(type))
                .reduce(Amount.inGrams(0), Amount::add);

        double factor = (double)requestedAmount.milliGrams() / (double) getAmount().milliGrams();
        return amount.multiply(factor);
    }

    public double caloriesForAmount(Amount requestedAmount) {
        double factor = (double)requestedAmount.milliGrams() / (double) getAmount().milliGrams();
        return this.calories() * factor;
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
}
