package test.java.service.export;

import main.java.service.export.ExportService;
import main.java.service.message.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;

/**
 * Test class for {@link ExportService}.
 *
 * @author vladimir.tikhomirov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-spring-config.xml")
public class ExportServiceImplTest {
    public static final String TEST_FILE_XML = "testfile.xml";
    @Autowired
    private ExportService exportService;

    @After
    public void tearDown() throws Exception {
        final String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir + "/" + TEST_FILE_XML);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    /**
     * Test for {@link ExportService#createAndExportXml(String, Collection)}.
     * Happy path.
     */
    @Test
    public void testCreateAndExportXml() throws Exception {
        //SETUP SUT
        Message message = createMessage();

        //EXERCISE
        StringBuilder streamResult = exportService.createAndExportXml(TEST_FILE_XML, Collections.singletonList(message));

        //VERIFY
        Assert.assertNotNull(streamResult);
        Assert.assertTrue(streamResult.toString().startsWith("<?xml version="));
        Assert.assertTrue(streamResult.toString().endsWith("</messages>"));
    }

    /**
     * Test for {@link ExportService#createAndExportHtmlFile(Collection)}.
     * Happy path.
     */
    @Test
    public void testCreateAndExportHtml() throws Exception {
        //SETUP SUT
        Message message = createMessage();

        //EXERCISE
        StringBuilder result = exportService.createAndExportHtmlFile(Collections.singletonList(message));

        //VERIFY
        Assert.assertNotNull(result);
        Assert.assertTrue(result.toString().startsWith("<html><body>"));
        Assert.assertTrue(result.toString().endsWith("</body></html>"));
    }

    private Message createMessage() {
        Message message = new Message();
        message.setTitle("title");
        message.setContent("content");
        message.setSender("author");
        message.setUrl("url");
        return message;
    }
}