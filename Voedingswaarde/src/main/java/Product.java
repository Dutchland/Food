import java.util.Arrays;
import java.util.Map;

public class Product {
    private final String name;
    private final MassPercentage fiber;
    private Map<MacroType, MassPercentage> macros;

    public Product(String name, Map<MacroType, MassPercentage> macros, MassPercentage fiber) {
        this.name = name;
        this.macros = macros;
        this.fiber = fiber;

        Conditions.notNullOrEmpty(name, "Product: ingredientname cannot be empty");
        Conditions.notBiggerThan(getMacro(MacroType.SATURATED_FAT).value(), getMacro(MacroType.FAT).value(), "Saturated fat cannot be bigger than total fat");
        Conditions.notBiggerThan(getMacro(MacroType.SUGAR).value(), getMacro(MacroType.CARBOHYDRATE).value(), "Sugar cannot be bigger than carbohydrates");
    }

    public MassPercentage getMacro(MacroType type) {
        return macros.entrySet().stream()
                .filter(entry -> entry.getKey().equals(type))
                .map(entry -> entry.getValue())
                .findFirst()
                .orElse(MassPercentage.zero());
    }

    public MassPercentage getFiber() {
        return this.fiber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Product: " + this.name + " ");

        Arrays.stream(MacroType.values())
                .forEach(mt -> builder
                        .append(mt.toString() + " ")
                        .append(getMacro(mt).toString() + " "));

        builder.append("Fiber " + fiber.toString());
        return builder.toString();
    }
}
