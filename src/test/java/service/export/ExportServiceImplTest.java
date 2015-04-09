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
import test.java.service.AbstractMessageServiceTest;

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
public class ExportServiceImplTest extends AbstractMessageServiceTest {
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
        final String uniqueMessage = "uniqueMessage";
        Message message = createMessage(uniqueMessage);

        //EXERCISE
        StringBuilder streamResult = exportService.createAndExportXml(TEST_FILE_XML, Collections.singletonList(message));

        //VERIFY
        Assert.assertNotNull(streamResult);
        final String resultString = streamResult.toString();
        Assert.assertTrue(resultString.startsWith("<?xml version="));
        Assert.assertTrue(resultString.endsWith("</messages>"));
        Assert.assertTrue(resultString.contains(uniqueMessage));
    }

    /**
     * Test for {@link ExportService#createAndExportHtmlFile(Collection)}.
     * Happy path.
     */
    @Test
    public void testCreateAndExportHtml() throws Exception {
        //SETUP SUT
        final String onlyGreatContent = "onlyGreatContent";
        Message message = createMessage(onlyGreatContent);

        //EXERCISE
        StringBuilder result = exportService.createAndExportHtmlFile(Collections.singletonList(message));

        //VERIFY
        Assert.assertNotNull(result);
        final String resultString = result.toString();
        Assert.assertTrue(resultString.startsWith("<html><body>"));
        Assert.assertTrue(resultString.endsWith("</body></html>"));
        Assert.assertTrue(resultString.contains(onlyGreatContent));
    }
}