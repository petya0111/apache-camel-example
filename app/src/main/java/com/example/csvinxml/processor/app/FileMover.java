package com.example.csvinxml.processor.app;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileMover extends SpringRouteBuilder {
    private @Value("${csvinxml.processor.app.output.directory}") String outputDirectory;
    private @Value("${csvinxml.processor.app.input.directory}") String inputDirectory;
    private @Autowired LoggingProcessor loggingProcessor;

    @Override
    public void configure() throws Exception {
        from("file:" + inputDirectory).to("file:" + outputDirectory).process(loggingProcessor);
    }

}