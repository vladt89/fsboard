package main.java.service;

import junit.framework.Assert;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class MessageServiceImplTest {

//    @Autowired
    private MessageService messageService = new MessageServiceImpl();

    @Test
    public void testCreateMessage() throws Exception {


        messageService.saveMessage(new Message());

        Assert.assertEquals(1, messageService.fetchAllMessages().size());

    }

    @Test
    public void testFetchAllMessages() throws Exception {

    }
}