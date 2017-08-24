package com.voeding;

import com.voeding.domain.*;
import com.voeding.domain.valueobjects.MassPercentage;
import com.voeding.utils.Lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileProductRepository implements ProductRepository {

    private final Lazy<List<Product>> products = new Lazy<>(this::readAllFromFile);

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.value());
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
        Product newProduct = new Product(newId, product.getName(), product.getMacros());

        saveToFile(newProduct);
        addToCache(newProduct);
        return newId;
    }

    private void addToCache(Product newProduct) {
        products.value().add(newProduct);
    }

    private void saveToFile(Product product) {
        ProductDto dto = ProductDto.fromEntity(product);
        // TODO: implement
    }



    private MacroDto mapToDto(Map.Entry<MacroType, MassPercentage> e) {
        return null;
    }

    private ProductId generateUniqueId() {
        long nanoTime = System.nanoTime();
        return new ProductId(nanoTime);
    }

    private List<Product> readAllFromFile() {
        // TODO: implement
        return new ArrayList<>();
    }
}
