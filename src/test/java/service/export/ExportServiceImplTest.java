package test.java.service.export;

import main.java.service.export.ExportService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author vladimir.tikhomirov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-spring-config.xml")
public class ExportServiceImplTest {
    public static final String TESTFILE_XML = "testfile.xml";
    @Autowired
    private ExportService exportService;

    @After
    public void tearDown() throws Exception {
        final String dir = System.getProperty("user.dir");
        Files.delete(Paths.get(dir + "/" + TESTFILE_XML));
    }

    @Test
    public void testExportXml() throws Exception {
        //EXERCISE
        File streamResult = exportService.createAndExportXml(TESTFILE_XML);
        //VERIFY
        Assert.assertNotNull(streamResult);
    }
}