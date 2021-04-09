package com.example.csvinxml.processor.app.camelgem;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;


/**
 * A Camel Java8 DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    public void configure() throws JAXBException {

        // Initialize JAXB
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlOrder.class);
        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(jaxbContext);

        //@formatter:off
        from("file:testdir").routeId("data-route")
                .unmarshal(new BindyFixedLengthDataFormat(Order.class))

                .to("log:test?showAll=true")

                .log("Loop through data")

                .process(new ProcessOrder())

                .marshal(jaxbDataFormat)
                .log("Order: \n${body}")

                .log("DATA processed")
        ;
    }
}