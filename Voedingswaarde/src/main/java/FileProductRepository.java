import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileProductRepository implements ProductRepository {

    private void parse() throws ParserConfigurationException, IOException, SAXException {

        File file = new File("userdata.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        String usr = document.getElementsByTagName("user").item(0).getTextContent();
        String pwd = document.getElementsByTagName("password").item(0).getTextContent();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getByName(String name) {
        return null;
    }
}
