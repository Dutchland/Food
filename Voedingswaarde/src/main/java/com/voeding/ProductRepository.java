package com.voeding;

import com.voeding.domain.Product;
import com.voeding.domain.ProductId;
import com.voeding.domain.ProtoProduct;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
    List<Product> findByName(String name);

    Product get(ProductId id);
    ProductId save(ProtoProduct product);
}
