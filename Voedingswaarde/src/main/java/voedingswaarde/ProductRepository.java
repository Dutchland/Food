package voedingswaarde;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();

    Product getByName(String name);

    Product get(ProductId id);

    ProductId add(Product product);
}
