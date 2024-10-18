package services;

import models.EquationParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlProcessor {

    private static final Logger logger = LoggerFactory.getLogger(XmlProcessor.class);

    public static EquationParameters processXml(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);

        Element root = doc.getDocumentElement();
        double a = getDoubleFromElement(root, "a");
        double b = getDoubleFromElement(root, "b");
        double c = getDoubleFromElement(root, "c");

        logger.debug("a : " + a + ", b : " + b + ", c : " + c);

        return new EquationParameters(a, b, c);
    }

    private static double getDoubleFromElement(Element root, String tagName) throws Exception {
        Node node = root.getElementsByTagName(tagName).item(0);
        if (node == null) {
            throw new IllegalArgumentException("Missing element: " + tagName);
        }
        return Double.parseDouble(node.getTextContent());
    }
}