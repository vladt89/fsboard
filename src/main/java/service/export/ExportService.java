package main.java.service.export;

import main.java.service.message.Message;

import java.util.Collection;

/**
 * @author vladimir.tikhomirov
 */
public interface ExportService {

    StringBuilder createAndExportXml(String filename, Collection<Message> messages);

    StringBuilder createAndExportHtmlFile(Collection<Message> messages);
}
