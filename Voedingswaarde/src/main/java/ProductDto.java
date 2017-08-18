import java.util.Map;

public class ProductDto {
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
