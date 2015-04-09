package main.java.service.export;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.OutputStream;

/**
 * @author vladimir.tikhomirov
 */
public class ExportServiceImpl implements ExportService {

    @Override
    public File createAndExportXml(String filename) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("messages");
        doc.appendChild(rootElement);

        Element message = doc.createElement("message");
        rootElement.appendChild(message);
        message.setAttribute("id", "1");

        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode("hello"));
        message.appendChild(title);

        Element content = doc.createElement("content");
        content.appendChild(doc.createTextNode("hello, my dear friend"));
        message.appendChild(content);

        Element author = doc.createElement("author");
        author.appendChild(doc.createTextNode("Vasya"));
        message.appendChild(author);

        Element url = doc.createElement("url");
        url.appendChild(doc.createTextNode("http://google.com"));
        message.appendChild(url);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        final String dir = System.getProperty("user.dir");
        File outputFile = new File(dir + filename);
        StreamResult result = new StreamResult(outputFile);
        transformer.transform(source, result);
        OutputStream outputStream = result.getOutputStream();
        return outputFile;
    }
}
