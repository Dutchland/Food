package voedingswaarde;

public class Calories {

    public static Calories ofIngredient(Ingredient ingredient) {
        return new Calories();
    }

    public double value() {
        return 0d;
    }
}
