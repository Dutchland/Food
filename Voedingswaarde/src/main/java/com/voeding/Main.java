package com.voeding;

import com.voeding.domain.*;
import com.voeding.domain.valueobjects.Amount;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ProductRepository productRepository = new InMemoryProductRepository();
        RecipeRepository recipeRepository = new InMemoryRecipeRepository(productRepository);

        Product egg = productRepository.findByName("Egg").get(0);
        Product meel = productRepository.findByName("Volkoren tarwemeel").get(0);
        Product melk = productRepository.findByName("Magere melk").get(0);

        Map<ProductId, Amount> pannenKoekenIngredients = new HashMap<>();
        pannenKoekenIngredients.put(egg.getId(), Amount.inGrams(50));
        pannenKoekenIngredients.put(meel.getId(), Amount.inGrams(100));
        pannenKoekenIngredients.put(melk.getId(), Amount.inGrams(100));

        ProtoRecipe pannenkoeken = new ProtoRecipe("Pannenkoeken", pannenKoekenIngredients);
        recipeRepository.save(pannenkoeken);

        for (Recipe recipe : recipeRepository.getAll()) {
            System.out.println();
            System.out.println(recipe);
            System.out.println(recipe.calories());
            System.out.println();
            System.out.println("Per 100 gram:");
            System.out.println(recipe.caloriesForAmount(Amount.HUNDRED_GRAMS()));

            Arrays.stream(MacroType.values())
                    .forEach(mt -> System.out.println(recipe.getMacroAmountForAmount(mt, Amount.HUNDRED_GRAMS()) + " " + mt.toString()));
        }
    }
}
