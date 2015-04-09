package main.java.service.message;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Class which provides functionality to work with messages.
 *
 * @author vladimir.tikhomirov
 */
public class MessageServiceImpl implements MessageService {
    private Collection<Message> messageLibrary = new LinkedList<>();

    @Override
    public Collection<Message> fetchAllMessages() {
        return messageLibrary;
    }

    @Override
    public void createAndSaveMessage(String title, String content, String author, String url) {
        Message message = createMessage(title, content, author, url);
        messageLibrary.add(message);
    }

    private Message createMessage(String title, String content, String author, String url) {
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        message.setSender(author);
        message.setUrl(url);
        return message;
    }
}
