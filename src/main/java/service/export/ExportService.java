package main.java.service.export;

import main.java.service.message.Message;

import java.util.Collection;

/**
 * Interface which defines the methods to deal with creation and exporting files with messages.
 *
 * @author vladimir.tikhomirov
 */
public interface ExportService {

    /**
     * Creates xml file, fills it with provided information and exports it.
     *
     * @param filename name for xml file to be created
     * @param messages collection of messages which are going to be placed in the file
     * @return created xml
     */
    StringBuilder createAndExportXml(String filename, Collection<Message> messages);

    /**
     * Creates html file, fills it with provided information and exports it.
     *
     * @param messages collection of messages which are going to be placed in the file
     * @return created html
     */
    StringBuilder createAndExportHtmlFile(Collection<Message> messages);
}
