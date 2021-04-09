package com.example.csvinxml.processor.app.transformation.xstream;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class XstreamTransformingMover extends SpringRouteBuilder {
    private @Value("${csvinxml.processor.app.transform.destination}") String destination;
    private @Value("${csvinxml.processor.app.transform.source}") String source;

//    Take a csv file, and split each row into a separate xml file.
    @Override
    public void configure() throws Exception {
        from(source).unmarshal().csv().split().method("csvToXmlTransformer", "convert").streaming().to(destination + "?fileName=${bean:uniqueFileNameGenerator?method=generate}.xml");
    }
}
