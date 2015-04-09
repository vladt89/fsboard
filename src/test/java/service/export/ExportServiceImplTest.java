package test.java.service.export;

import junit.framework.Assert;
import main.java.service.export.ExportService;
import main.java.service.export.ExportServiceImpl;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author vladimir.tikhomirov
 */
public class ExportServiceImplTest {
    public static final String TESTFILE_XML = "testfile.xml";
    private ExportService exportService = new ExportServiceImpl();

    @After
    public void tearDown() throws Exception {
        final String dir = System.getProperty("user.dir");
        Files.delete(Paths.get(dir + TESTFILE_XML));
    }

    @Test
    public void testExportXml() throws Exception {
        //EXERCISE
        File streamResult = exportService.createAndExportXml(TESTFILE_XML);
        //VERIFY
        Assert.assertNotNull(streamResult);
    }
}