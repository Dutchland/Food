public class Ingredient {

    private final Product product;
    private final Amount amount;

    public Ingredient(Product product, Amount amount) {
        Conditions.notNull(product, "Ingredient: product cannot be empty");
        Conditions.notNull(amount, "Ingredient: amount cannot be empty");
        this.product = product;
        this.amount = amount;
    }

    public double calories() {
        double carbohydrates = getAmount(MacroType.CARBOHYDRATE).milliGrams() * MacroType.CARBOHYDRATE.getCaloriesPerGram() / 1000d;
        double fat = getAmount(MacroType.FAT).milliGrams() * MacroType.FAT.getCaloriesPerGram() / 1000d;
        double protein = getAmount(MacroType.PROTEIN).milliGrams() * MacroType.PROTEIN.getCaloriesPerGram() / 1000d;

        return carbohydrates + fat + protein;
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
