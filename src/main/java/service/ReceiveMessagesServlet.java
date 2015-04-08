package main.java.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class ReceiveMessagesServlet extends HttpServlet {

    public static final String CREATE_MESSAGE = "create message";
    public static final String LIST_MESSAGES = "list messages";
    public static final String CREATE_NEW_MESSAGE = "create new message";

//    @Autowired
    private MessageService messageService = new MessageServiceImpl();

    public ReceiveMessagesServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("command");
        if (CREATE_MESSAGE.equals(command)) {
            messageService.saveMessage(createMessage(request));
            request.getRequestDispatcher("/WEB-INF/creationSuccess.jsp").forward(request, response);
        }

        if (LIST_MESSAGES.equals(command)) {
            Collection<Message> messages = messageService.fetchAllMessages();

            StringBuilder builder = new StringBuilder();
            String responseToClient = "<html><body>These are your messages: <br>";
            builder.append(responseToClient);
            for (Message message : messages) {
                builder.append(message.getTitle()).append(" ");
                builder.append(message.getContent()).append(" ");
                builder.append(message.getSender()).append(" ");
                builder.append("<br>");
            }
            builder.append("</body></html>");

            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
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