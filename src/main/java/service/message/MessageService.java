package main.java.service.message;

import java.util.Collection;

/**
 * Interface which defines the methods to work with messages.
 *
 * @author vladimir.tikhomirov
 */
public interface MessageService {

    /**
     * Fetches all the messages which were created and saved.
     *
     * @return collection of saved messages if they exist, empty collection otherwise
     */
    Collection<Message> fetchAllMessages();

    /**
     * Creates and saves a message with the provided data.
     *
     * @param title message title
     * @param content content of the message
     * @param author the author of the message
     * @param url the url where the request was done
     */
    void createAndSaveMessage(String title, String content, String author, String url);
}
