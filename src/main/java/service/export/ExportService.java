package main.java.service.export;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;

/**
 * @author vladimir.tikhomirov
 */
public interface ExportService {

    File createAndExportXml(String filename) throws TransformerException, ParserConfigurationException;
}
