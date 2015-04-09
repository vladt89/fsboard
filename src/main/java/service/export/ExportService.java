package main.java.service.export;

import main.java.service.message.Message;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.util.Collection;

/**
 * @author vladimir.tikhomirov
 */
public interface ExportService {

    File createAndExportXml(String filename, Collection<Message> messages) throws TransformerException, ParserConfigurationException;
}
