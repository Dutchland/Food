package com.voeding;

import com.voeding.domain.MacroType;
import com.voeding.domain.Product;
import com.voeding.domain.ProductId;
import com.voeding.domain.valueobjects.MassPercentage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDto {
    private String name;
    private long id;
    private List<MacroDto> macros;

    public ProductDto(String name, long id, List<MacroDto> macros) {
        this.name = name;
        this.id = id;
        this.macros = macros;
    }

    public static ProductDto fromEntity(Product product) {
        String name = product.getName();
        long id = product.getId().value();
        List<MacroDto> macros = Arrays.stream(MacroType.values())
                .map(mt -> MacroDto.fromEntities(mt, product.getMacro(mt)))
                .collect(Collectors.toList());

        return new ProductDto(name, id, macros);
    }

    public Product toEntity() {
        ProductId id = new ProductId(this.id);

        Map<MacroType, MassPercentage> macros = new HashMap<>();
        this.macros.forEach(m -> macros.put(
                MacroType.valueOf(m.getMacroType()),
                new MassPercentage(m.getMassPercentage())));

        return new Product(id, this.name, macros);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MacroDto> getMacros() {
        return macros;
    }

    public void setMacros(List<MacroDto> macros) {
        this.macros = macros;
    }
}
