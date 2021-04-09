package com.example.csvinxml.processor.app;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MultipleDestinationDeliverer extends SpringRouteBuilder {
    private @Value("${csvinxml.processor.app.multicast.destination.one}") String destinationOne;
    private @Value("${csvinxml.processor.app.multicast.destination.two}") String destinationTwo;
    private @Value("${csvinxml.processor.app.multicast.source}") String source;

    @Override
    public void configure() throws Exception {
        from(source).multicast().to(destinationOne, destinationTwo);
    }
}
