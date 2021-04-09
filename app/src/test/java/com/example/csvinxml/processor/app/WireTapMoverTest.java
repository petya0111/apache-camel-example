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
//public class WireTapMoverTest extends CamelFileBasedTestCase {
//    @Value("${csvinxml.processor.app.wire.tap.original.destination}")
//    private File destination;
//
//    @Value("${csvinxml.processor.app.wire.tap.tapped.destination}")
//    private File tappedDestination;
//
//    @Value("${csvinxml.processor.app.wire.tap.source}")
//    private File inputDirectory;
//
//    @Before
//    public void setup() throws IOException {
//        makeOrCleanDirectory(inputDirectory);
//        makeOrCleanDirectory(destination);
//        makeOrCleanDirectory(tappedDestination);
//    }
//
//    @Test
//    public void tapIt() {
//        loadFileToProcess("test.txt", "test text");
//        runCamelAndWaitForItToFinish();
//
//        assertEquals(1, destination.listFiles().length);
//        assertEquals(1, tappedDestination.listFiles().length);
//    }
//
//    @Override
//    protected File getInputDirectory() {
//        return inputDirectory;
//    }
//}
