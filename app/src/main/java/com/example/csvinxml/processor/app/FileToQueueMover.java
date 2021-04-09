package com.example.csvinxml.processor.app;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileToQueueMover extends SpringRouteBuilder {
    private @Value("${csvinxml.processor.app.queue.destination}") String destination;
    private @Value("${csvinxml.processor.app.queue.source}") String source;

    @Override
    public void configure() throws Exception {
        from(source).id(source).to(destination).id(destination).routeId(FileToQueueMover.class.getSimpleName());
    }

}