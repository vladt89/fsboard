package test.java.service;

import main.java.service.message.Message;

/**
 * Helper class for other test classes to setup the data to facilitate exercising.
 *
 * @author vladimir.tikhomirov
 */
public class AbstractMessageServiceTest {

    protected Message createMessage(String content) {
        Message message = new Message();
        message.setTitle("title");
        message.setContent(content);
        message.setSender("author");
        message.setUrl("url");
        return message;
    }
}
