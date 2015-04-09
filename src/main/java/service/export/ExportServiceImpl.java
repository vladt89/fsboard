package main.java.service.export;

import main.java.service.message.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * @author vladimir.tikhomirov
 */
public class ExportServiceImpl implements ExportService {

    @Override
    public StringBuilder createAndExportXml(String filename, Collection<Message> messages) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new StringBuilder();
        }

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("messages");
        doc.appendChild(rootElement);

        int i = 1;
        for (Message message : messages) {
            Element elementMessage = doc.createElement("message");
            rootElement.appendChild(elementMessage);
            elementMessage.setAttribute("id", String.valueOf(i++));

            elementMessage.appendChild(createElement(doc, "title", message.getTitle()));
            elementMessage.appendChild(createElement(doc, "content", message.getContent()));
            elementMessage.appendChild(createElement(doc, "author", message.getSender()));
            elementMessage.appendChild(createElement(doc, "url", message.getUrl()));
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return new StringBuilder();
        }
        DOMSource source = new DOMSource(doc);

        final String dir = System.getProperty("user.dir");
        File outputFile = new File(dir + "/" + filename);
        try {
            transformer.transform(source, new StreamResult(outputFile));
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        StringBuilder output = new StringBuilder();
        try {
            for (String line : Files.readAllLines(Paths.get(outputFile.getPath()), Charset.defaultCharset())) {
                output.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    private Element createElement(Document doc, String type, String value) {
        Element docElement = doc.createElement(type);
        docElement.appendChild(doc.createTextNode(value));
        return docElement;
    }
}
