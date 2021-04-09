//package com.example.csvinxml.processor.app;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.File;
//import java.io.IOException;
//
//import static junit.framework.Assert.assertEquals;
//import static com.example.csvinxml.processor.app.FileUtil.makeOrCleanDirectory;
//
//public class ContentBasedFileMoverTest extends CamelFileBasedTestCase {
//    @Value("${csvinxml.processor.app.content.based.xml.destination}")
//    private File xmlOutputDirectory;
//
//    @Value("${csvinxml.processor.app.content.based.csv.destination}")
//    private File csvOutputDirectory;
//
//    @Value("${csvinxml.processor.app.content.based.source}")
//    private File inputDirectory;
//
//    @Before
//    public void setup() throws IOException {
//        makeOrCleanDirectory(inputDirectory);
//        makeOrCleanDirectory(csvOutputDirectory);
//        makeOrCleanDirectory(xmlOutputDirectory);
//    }
//
//    @Test
//    public void process_csv() {
//        loadFileToProcess("test.csv", "csv");
//        runCamelAndWaitForItToFinish();
//        assertEquals(1, csvOutputDirectory.listFiles().length);
//        assertEquals(0, xmlOutputDirectory.listFiles().length);
//    }
//
//    @Test
//    public void process_xml() {
//        loadFileToProcess("test.xml", "xml");
//        runCamelAndWaitForItToFinish();
//        assertEquals(0, csvOutputDirectory.listFiles().length);
//        assertEquals(1, xmlOutputDirectory.listFiles().length);
//    }
//
//    @Override
//    protected File getInputDirectory() {
//        return inputDirectory;
//    }
//}
