package main.java.service.servlets;

import main.java.service.export.ExportService;
import main.java.service.export.ExportServiceImpl;
import main.java.service.message.Message;
import main.java.service.message.MessageService;
import main.java.service.message.MessageServiceImpl;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

public class ReceiveMessagesServlet extends HttpServlet {

    public static final String CREATE_MESSAGE = "create message";
    public static final String LIST_MESSAGES = "list messages";
    public static final String CREATE_NEW_MESSAGE = "create new message";
    public static final String HTML_TYPE = "html";
    public static final String XML_TYPE = "xml";

    //    @Autowired
    private MessageService messageService = new MessageServiceImpl();

    private ExportService exportService = new ExportServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");
        if (CREATE_MESSAGE.equals(command)) {
            messageService.saveMessage(createMessage(request));
            request.getRequestDispatcher("/WEB-INF/creationSuccess.jsp").forward(request, response);
        }

        if (LIST_MESSAGES.equals(command)) {
            Collection<Message> messages = messageService.fetchAllMessages();

            String exportType = request.getParameter("exportType");
            if (HTML_TYPE.equals(exportType)) {
                StringBuilder builder = new StringBuilder();
                String responseToClient = "<html><body>These are your messages: <br>";
                builder.append(responseToClient);
                for (Message message : messages) {
                    builder.append(message.getTitle()).append(" ");
                    builder.append(message.getContent()).append(" ");
                    builder.append(message.getSender()).append(" ");
                    builder.append(message.getUrl()).append(" ");
                    builder.append("<br>");
                }
                builder.append("</body></html>");

                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = response.getWriter();
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            }

            if (XML_TYPE.equals(exportType)) {
                File xmlFile = null;
                try {
                    xmlFile = exportService.createAndExportXml("tempFile.xml");
                } catch (TransformerException | ParserConfigurationException e) {
                    e.printStackTrace();
                }

                StringBuilder realResult = new StringBuilder();
                if (xmlFile != null) {
                    for (String line : FileUtils.readLines(xmlFile)) {
                        realResult.append(line);
                    }
                }

                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = response.getWriter();
                writer.write(realResult.toString());
                writer.flush();
                writer.close();
            }

        }

        if (CREATE_NEW_MESSAGE.equals(command)) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
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