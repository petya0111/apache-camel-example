package com.example.csvinxml.processor.app.transformation.jaxb;

import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JaxbTransformingMover extends SpringRouteBuilder {
    private
    @Value("${csvinxml.processor.app.jaxb.transform.destination}")
    String destination;
    private
    @Value("${csvinxml.processor.app.jaxb.transform.source}")
    String source;

    @Override
    public void configure() throws Exception {
        JaxbDataFormat dataFormatType = new JaxbDataFormat();
        dataFormatType.setContextPath("com.example.csvinxml.processor.app.transformation");
        from(source).unmarshal().csv().split().method("csvToPersonTransformer", "convert").streaming().marshal(dataFormatType).to(destination + "?fileName=${bean:uniqueFileNameGenerator?method=generate}.xml");
    }
}
