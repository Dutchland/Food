package com.voeding;

import com.voeding.domain.*;
import com.voeding.domain.valueobjects.Amount;
import com.voeding.utils.NonEmptyList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryRecipeRepository implements RecipeRepository {
    private final List<Recipe> recipes = new ArrayList<>();
    private final ProductRepository productRepository;

    public InMemoryRecipeRepository(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(recipes);
    }

    @Override
    public RecipeId save(ProtoRecipe recipe) {
        RecipeId newId = generateUniqueId();

        Recipe newRecipe = create(newId, recipe);
        this.recipes.add(newRecipe);

        return newId;
    }

    @Override
    public Recipe get(RecipeId id) {
        return this.recipes.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Recipe with id " + id + " does not exist"));
    }

    private Recipe create(RecipeId newId, ProtoRecipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients().entrySet().stream()
                .map(e -> makeIngredient(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return new Recipe(newId, recipe.getName(), new NonEmptyList<>(ingredients));
    }

    private RecipeId generateUniqueId() {
        long nanoTime = System.nanoTime();
        return new RecipeId(nanoTime);
    }

    private Ingredient makeIngredient(ProductId productId, Amount amount) {
        Product product = productRepository.get(productId);
        return new Ingredient(product, amount);
    }
}
