package voedingswaarde.product;

import voedingswaarde.MacroType;
import voedingswaarde.Product;
import voedingswaarde.ProductId;
import voedingswaarde.ProductRepository;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileProductRepository implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        try(FileInputStream inputStream = new FileInputStream(new File("Products.xml"))) {
            XMLDecoder decoder = new XMLDecoder(inputStream);
            Object object = decoder.readObject();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getByName(String name) {
        return null;
    }

    @Override
    public Product get(ProductId id) {
        return null;
    }

    @Override
    public ProductId add(Product product) {
        ProductId maxProductId = products.stream()
                .map(Product::getId)
                .max(ProductId::compareTo)
                .orElseGet(() -> new ProductId(1));

        return new ProductId(maxProductId.value() + 1);
    }

    class ProductDto {
        private String name;
        private Map<MacroType, Double> macros;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<MacroType, Double> getMacros() {
            return macros;
        }

        public void setMacros(Map<MacroType, Double> macros) {
            this.macros = macros;
        }
    }
}
