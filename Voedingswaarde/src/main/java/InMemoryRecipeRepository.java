import java.util.ArrayList;
import java.util.List;

public class InMemoryRecipeRepository implements RecipeRepository {
    private final List<Recipe> recipes = new ArrayList<>();

    @Override
    public List<Recipe> getAll() {
        return this.recipes;
    }

    @Override
    public void add(Recipe recipe) {
        this.recipes.add(recipe);
    }
}
