package main.java.service.servlets;

import main.java.service.export.ExportService;
import main.java.service.export.ExportServiceImpl;
import main.java.service.message.Message;
import main.java.service.message.MessageService;
import main.java.service.message.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class ReceiveMessagesServlet extends HttpServlet {

    public static final String HTML_TYPE = "html";
    public static final String XML_TYPE = "xml";

    private MessageService messageService = new MessageServiceImpl();
    private ExportService exportService = new ExportServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (Command.CREATE_MESSAGE.getValue().equals(command)) {
            messageService.saveMessage(createMessage(request));
            request.getRequestDispatcher("/WEB-INF/creationSuccess.jsp").forward(request, response);
        }

        if (Command.LIST_MESSAGES.getValue().equals(command)) {
            String exportType = request.getParameter("exportType");
            if (HTML_TYPE.equals(exportType)) {
                fillResponse(response, createAndExportHtmlFile());
            }
            if (XML_TYPE.equals(exportType)) {
                fillResponse(response, createAndExportXmlFile());
            }
        }

        if (Command.CREATE_NEW_MESSAGE.getValue().equals(command)) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private StringBuilder createAndExportHtmlFile() {
        StringBuilder builder = new StringBuilder();
        Collection<Message> messages = messageService.fetchAllMessages();
        if (messages.isEmpty()) {
            builder.append("You haven't create any messages yet. Please, return to the previous page and create a new message.");
            return builder;
        }

        builder.append("<html><body>These are your messages: <br>");
        for (Message message : messages) {
            builder.append(message.getTitle()).append(" ");
            builder.append(message.getContent()).append(" ");
            builder.append(message.getSender()).append(" ");
            builder.append(message.getUrl()).append(" ");
            builder.append("<br>");
        }
        builder.append("</body></html>");
        return builder;
    }

    private void fillResponse(HttpServletResponse response, StringBuilder builder) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    private StringBuilder createAndExportXmlFile() throws IOException {
        File xmlFile = null;
        try {
            xmlFile = exportService.createAndExportXml("tempFile.xml");
        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        StringBuilder output = new StringBuilder();
        if (xmlFile != null) {
            for (String line : Files.readAllLines(Paths.get(xmlFile.getPath()), Charset.defaultCharset())) {
                output.append(line);
            }
        }
        return output;
    }

    private Message createMessage(HttpServletRequest request) {
        Message message = new Message();
        message.setTitle(request.getParameter("title"));
        message.setContent(request.getParameter("content"));
        message.setSender(request.getParameter("author"));
        message.setUrl(request.getRequestURL().toString());
        return message;
    }
}