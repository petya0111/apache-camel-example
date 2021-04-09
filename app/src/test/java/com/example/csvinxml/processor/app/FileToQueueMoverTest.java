//package com.example.csvinxml.processor.app;
//
//import org.apache.camel.EndpointInject;
//import org.apache.camel.component.mock.MockEndpoint;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.File;
//
//public class FileToQueueMoverTest extends CamelFileBasedTestCase {
//    @Value("${csvinxml.processor.app.queue.source}")
//    private File inputDirectory;
//
//    @Value("${csvinxml.processor.app.queue.destination}")
//    private String destination;
//
//    @EndpointInject(uri = "mock:result")
//    private MockEndpoint result;
//
//    @Test
//    public void route() throws Exception {
//        replaceByIdInRoute("FileToQueueMover", destination, result.getEndpointUri());
//
//        result.setExpectedMessageCount(1);
//
//        loadFileToProcess("test.txt", "text");
//        runCamelAndWaitForItToFinish();
//
//        result.assertIsSatisfied();
//    }
//
//    @Override
//    protected File getInputDirectory() {
//        return inputDirectory;
//    }
//
//}
