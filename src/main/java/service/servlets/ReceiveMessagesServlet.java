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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Class which is responsible for communication with UI.
 *
 * @author vladimir.tikhomirov
 */
public class ReceiveMessagesServlet extends HttpServlet {

    public static final String HTML_TYPE = "html";
    public static final String XML_TYPE = "xml";

    private MessageService messageService = new MessageServiceImpl();
    private ExportService exportService = new ExportServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (Command.CREATE_MESSAGE.getValue().equals(command)) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String author = request.getParameter("author");
            String url = request.getRequestURL().toString();
            messageService.createAndSaveMessage(title, content, author, url);
            request.getRequestDispatcher("/WEB-INF/creationSuccess.jsp").forward(request, response);
        }

        if (Command.LIST_MESSAGES.getValue().equals(command)) {
            String exportType = request.getParameter("exportType");
            Collection<Message> messages = messageService.fetchAllMessages();
            if (HTML_TYPE.equals(exportType)) {
                fillResponse(response, exportService.createAndExportHtmlFile(messages));
            }
            if (XML_TYPE.equals(exportType)) {
                fillResponse(response, exportService.createAndExportXml("tempFile.xml", messages));
            }
        }

        if (Command.CREATE_NEW_MESSAGE.getValue().equals(command)) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private void fillResponse(HttpServletResponse response, StringBuilder builder) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }
}