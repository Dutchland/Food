import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ProductRepository productRepository = new InMemoryProductRepository();

        ProductDto dto = new ProductDto();
        Map<MacroType, Double> macros = new HashMap<>();
        macros.put(MacroType.CARBOHYDRATE, 2.0d);
        dto.setName("arstr");
        dto.setMacros(macros);

        List<ProductDto> dtos = new ArrayList<>();
        dtos.add(dto);
        dtos.add(new ProductDto());

        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new File("Products.xml")));
        encoder.writeObject(dto);
        encoder.flush();

        XMLDecoder decoder = new XMLDecoder(new FileInputStream(new File("Products.xml")));
        Object o = decoder.readObject();

        Product egg = productRepository.getByName("Egg");
        Ingredient ingredient = new Ingredient(egg, Amount.inGrams(50));

        Product meel = productRepository.getByName("Volkoren tarwemeel");
        Ingredient ingredient2 = new Ingredient(meel, Amount.hundredGrams());

        Product melk = productRepository.getByName("Magere melk");
        Ingredient ingredient3 = new Ingredient(melk, Amount.hundredGrams());

        Recipe pannenkoeken = new Recipe("Pannenkoeken", ingredient, ingredient2, ingredient3);

        Amount hundredGrams = Amount.hundredGrams();

        System.out.println();
        System.out.println(pannenkoeken);
        System.out.println(pannenkoeken.calories() + " Calories");
        System.out.println();
        System.out.println("Per 100 gram:");
        System.out.println(pannenkoeken.caloriesForAmount(hundredGrams) + " Calories");

        Arrays.stream(MacroType.values())
                .forEach(mt -> System.out.println(pannenkoeken.getMacroAmountForAmount(mt, hundredGrams) + " " + mt.toString()));
    }
}
