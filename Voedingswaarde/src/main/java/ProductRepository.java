import java.util.List;

public interface ProductRepository {
    List<Product> getAll();

    Product getByName(String name);
}
