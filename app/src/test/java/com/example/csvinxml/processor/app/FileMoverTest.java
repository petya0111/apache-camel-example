package com.example.csvinxml.processor.app;


import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FileMoverTest extends CamelFileBasedTestCase {
    private
    @Value("${csvinxml.processor.app.output.directory}")
    File outputDirectory;
    private
    @Value("${csvinxml.processor.app.input.directory}")
    File inputDirectory;
    
    @Before
    public void setup() throws IOException {
        FileUtils.cleanDirectory(inputDirectory);
        FileUtils.cleanDirectory(outputDirectory);
    }

    @Test
    public void route() {
        loadFileToProcess("test.txt", "Hello World");
        runCamelAndWaitForItToFinish();
        assertTrue(1 == outputDirectory.listFiles().length);
    }

    @Override
    protected File getInputDirectory() {
        return inputDirectory;
    }
}
