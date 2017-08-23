package voedingswaarde.product;

import voedingswaarde.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private final Lazy<List<Product>> products = new Lazy<>(this::initialize);

    @Override
    public List<Product> getAll() {
        return products.value();
    }

    @Override
    public Product getByName(String name) {
        return products.value().stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .get();
    }

    @Override
    public Product get(ProductId id) {
        return null;
    }

    @Override
    public ProductId add(Product product) {
        return null;
    }

    private List<Product> initialize() {
        List<Product> products = new ArrayList<>();

        products.add(makeVolkorenTarwemeel());
        products.add(makeMagereMelk());
        products.add(makeEgg());

        return products;
    }

    private Product makeMagereMelk() {
        Map<MacroType, MassPercentage> macros = new HashMap<>();
        macros.put(MacroType.CARBOHYDRATE, new MassPercentage(0.051));
        macros.put(MacroType.SUGAR, new MassPercentage(0.051));
        macros.put(MacroType.FAT, new MassPercentage(0.001));
        macros.put(MacroType.SATURATED_FAT, new MassPercentage(0.001));
        macros.put(MacroType.PROTEIN, new MassPercentage(0.039));

        return new Product("Magere melk", macros, new MassPercentage(0.01d));
    }

    private Product makeVolkorenTarwemeel() {
        Map<MacroType, MassPercentage> macros = new HashMap<>();
        macros.put(MacroType.CARBOHYDRATE, new MassPercentage(0.622));
        macros.put(MacroType.SUGAR, new MassPercentage(0.015));
        macros.put(MacroType.FAT, new MassPercentage(0.015));
        macros.put(MacroType.SATURATED_FAT, new MassPercentage(0.002));
        macros.put(MacroType.PROTEIN, new MassPercentage(0.114));

        return new Product("Volkoren tarwemeel", macros, new MassPercentage(0.01d));
    }


    private Product makeEgg() {
        Map<MacroType, MassPercentage> macros = new HashMap<>();
        macros.put(MacroType.CARBOHYDRATE, new MassPercentage(0.015));
        macros.put(MacroType.SUGAR, new MassPercentage(0.001));
        macros.put(MacroType.FAT, new MassPercentage(0.112));
        macros.put(MacroType.SATURATED_FAT, new MassPercentage(0.035));
        macros.put(MacroType.PROTEIN, new MassPercentage(0.129));

        return new Product("Egg", macros, new MassPercentage(0.01d));
    }
}
