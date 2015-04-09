package test.java.service.message;

import main.java.service.message.Message;
import main.java.service.message.MessageService;
import main.java.service.message.MessageServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.java.service.AbstractMessageServiceTest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Test class for {@link main.java.service.message.MessageService}.
 *
 * @author vladimir.tikhomirov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-spring-config.xml")
public class MessageServiceImplTest extends AbstractMessageServiceTest {
    @Autowired
    private MessageServiceImpl messageService;

    @After
    public void tearDown() throws Exception {
        final Collection<Message> messageLibrary = messageService.getMessageLibrary();
        if (!messageLibrary.isEmpty()) {
            messageService.setMessageLibrary(Collections.<Message>emptyList());
        }
    }

    /**
     * Test for {@link MessageService#fetchAllMessages()}.
     * Happy path.
     */
    @Test
    public void testFetchAllMessages() throws Exception {
        //SETUP SUT
        final Collection<Message> messageLibrary = messageService.getMessageLibrary();
        final String content = "content";
        final Message message = createMessage(content);
        messageLibrary.add(message);

        //EXERCISE
        final List<Message> messageList = (List<Message>) messageService.fetchAllMessages();

        //VERIFY
        Assert.assertNotNull(messageList);
        Assert.assertFalse(messageList.isEmpty());
        Assert.assertEquals(content, messageList.get(0).getContent());
    }

    /**
     * Test for {@link MessageService#fetchAllMessages()} when there were no messages created.
     */
    @Test
    public void testFetchAllMessagesWhenMessageLibraryIsEmpty() throws Exception {
        //EXERCISE
        final List<Message> messageList = (List<Message>) messageService.fetchAllMessages();

        //VERIFY
        Assert.assertTrue(messageList.isEmpty());
    }
}
