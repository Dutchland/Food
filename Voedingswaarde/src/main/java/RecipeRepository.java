import java.util.List;

public interface RecipeRepository {
    List<Recipe> getAll();
    void add(Recipe recipe);
}
