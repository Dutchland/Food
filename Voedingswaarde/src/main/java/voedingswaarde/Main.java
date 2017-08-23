package voedingswaarde;

import voedingswaarde.product.FileProductRepository;
import voedingswaarde.product.InMemoryProductRepository;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ProductRepository productRepository = new InMemoryProductRepository();

        ProductRepository fileRepository = new FileProductRepository();
        fileRepository.getAll();

        Product egg = productRepository.getByName("Egg");
        Ingredient ingredient = new Ingredient(egg, Amount.inGrams(50));

        Product meel = productRepository.getByName("Volkoren tarwemeel");
        Ingredient ingredient2 = new Ingredient(meel, Amount.hundredGrams());

        Product melk = productRepository.getByName("Magere melk");
        Ingredient ingredient3 = new Ingredient(melk, Amount.hundredGrams());

        Recipe pannenkoeken = new Recipe("Pannenkoeken", ingredient, ingredient2, ingredient3);

        Amount hundredGrams = Amount.hundredGrams();

        System.out.println();
        System.out.println(pannenkoeken);
        System.out.println(pannenkoeken.calories() + " Calories");
        System.out.println();
        System.out.println("Per 100 gram:");
        System.out.println(pannenkoeken.caloriesForAmount(hundredGrams) + " Calories");

        Arrays.stream(MacroType.values())
                .forEach(mt -> System.out.println(pannenkoeken.getMacroAmountForAmount(mt, hundredGrams) + " " + mt.toString()));
    }
}
