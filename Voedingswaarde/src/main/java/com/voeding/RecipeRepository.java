package com.voeding;

import com.voeding.domain.ProtoRecipe;
import com.voeding.domain.Recipe;
import com.voeding.domain.RecipeId;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> getAll();
    RecipeId save(ProtoRecipe recipe);
    Recipe get(RecipeId id);
}
