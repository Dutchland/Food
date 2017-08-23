package voedingswaarde;

public class ProductId implements Comparable<ProductId> {
    private final int id;

    public ProductId(int id) {
        Conditions.isPositive(id, "Not a valid ProductId: " + id);
        this.id = id;
    }

    public int value() {
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
        return id;
    }

    @Override
    public int compareTo(ProductId otherProductId) {
        return Integer.compare(id, otherProductId.id);
    }
}
