package com.voeding.domain;

import com.voeding.utils.Conditions;

public class ProductId {

    private final long id;

    public ProductId(long id) {
        Conditions.isPositive(id, "Not a valid productId: " + id);
        this.id = id;
    }

    public long value() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductId productId = (ProductId) o;

        return id == productId.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
