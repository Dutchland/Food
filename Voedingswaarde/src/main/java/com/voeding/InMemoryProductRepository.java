package com.voeding;

import com.voeding.domain.*;
import com.voeding.utils.Lazy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements ProductRepository {

    private static final Lazy<List<Product>> products = new Lazy<>(() -> initialize());

    @Override
    public List<Product> getAll() {
        return products.value();
    }

    @Override
    public List<Product> findByName(String name) {
        return products.value().stream()
                .filter(product -> product.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Product get(ProductId id) {
        return products.value().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with id: " + id.toString() + " does not exist"));
    }

    @Override
    public ProductId save(ProtoProduct product) {
        ProductId newId = generateUniqueId();

        // Save in cache
        Product newProduct = new Product(newId, product.getName(), product.getMacros());
        products.value().add(newProduct);

        return newId;
    }

    private ProductId generateUniqueId() {
        long nanoTime = System.nanoTime();
        return new ProductId(nanoTime);
    }

    private static List<Product> initialize() {
        List<Product> products = new ArrayList<>();

        products.add(makeVolkorenTarwemeel());
        products.add(makeMagereMelk());
        products.add(makeEgg());

        return products;
    }

    private static Product makeMagereMelk() {
        Map<MacroType, MassPercentage> macros = new HashMap<>();
        macros.put(MacroType.CARBOHYDRATE, new MassPercentage(BigDecimal.valueOf(0.051)));
        macros.put(MacroType.SUGAR, new MassPercentage(BigDecimal.valueOf(0.051)));
        macros.put(MacroType.FAT, new MassPercentage(BigDecimal.valueOf(0.001)));
        macros.put(MacroType.SATURATED_FAT, new MassPercentage(BigDecimal.valueOf(0.001)));
        macros.put(MacroType.PROTEIN, new MassPercentage(BigDecimal.valueOf(0.039)));

        return new Product(new ProductId(1), "Magere melk", macros);
    }

    private static Product makeVolkorenTarwemeel() {
        Map<MacroType, MassPercentage> macros = new HashMap<>();
        macros.put(MacroType.CARBOHYDRATE, new MassPercentage(BigDecimal.valueOf(0.622)));
        macros.put(MacroType.SUGAR, new MassPercentage(BigDecimal.valueOf(0.015)));
        macros.put(MacroType.FAT, new MassPercentage(BigDecimal.valueOf(0.015)));
        macros.put(MacroType.SATURATED_FAT, new MassPercentage(BigDecimal.valueOf(0.002)));
        macros.put(MacroType.PROTEIN, new MassPercentage(BigDecimal.valueOf(0.114)));

        return new Product(new ProductId(2), "Volkoren tarwemeel", macros);
    }

    private static Product makeEgg() {
        Map<MacroType, MassPercentage> macros = new HashMap<>();
        macros.put(MacroType.CARBOHYDRATE, new MassPercentage(BigDecimal.valueOf(0.015)));
        macros.put(MacroType.SUGAR, new MassPercentage(BigDecimal.valueOf(0.001)));
        macros.put(MacroType.FAT, new MassPercentage(BigDecimal.valueOf(0.112)));
        macros.put(MacroType.SATURATED_FAT, new MassPercentage(BigDecimal.valueOf(0.035)));
        macros.put(MacroType.PROTEIN, new MassPercentage(BigDecimal.valueOf(0.129)));

        return new Product(new ProductId(3), "Egg", macros);
    }
}
