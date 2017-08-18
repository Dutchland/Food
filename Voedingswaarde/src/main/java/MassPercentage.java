import java.math.BigDecimal;

public class MassPercentage {
    private static final double MAX_VALUE = 1d;
    private final double percentage;

    public MassPercentage(double percentage) {
        Conditions.notBiggerThan(percentage, MAX_VALUE, "MassPercentage cannot be bigger than " + MAX_VALUE + ": " + percentage);
        Conditions.isPositive(percentage, "MassPercentage cannot be negative: " + percentage);
        this.percentage = percentage;
    }

    public double value() {
        return this.percentage;
    }

    public static MassPercentage HUNDRED_PROCENT() {
        return new MassPercentage(MAX_VALUE);
    }

    public MassPercentage add(MassPercentage otherPercentage) {
        return new MassPercentage(this.value() + otherPercentage.value());
    }

    @Override
    public String toString() {
        return BigDecimal.valueOf(percentage * 100).setScale(1, BigDecimal.ROUND_HALF_UP) + "%";
    }

    public static MassPercentage zero() {
        return new MassPercentage(0d);
    }
}
