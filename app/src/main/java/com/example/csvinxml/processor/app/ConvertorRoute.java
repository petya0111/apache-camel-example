package com.example.csvinxml.processor.app;

import com.example.csvinxml.processor.app.transformation.Invoice;
import com.thoughtworks.xstream.XStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.XStreamDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Petya Marinova
 */
public class ConvertorRoute implements RoutesBuilder {

    @Override
    public void addRoutesToCamelContext(CamelContext context) throws Exception {
        context.addRoutes(new RouteBuilder() {
            public void configure() {

                try {
                    XStreamDataFormat xstream = new XStreamDataFormat();
                    xstream.setAliases(Collections.singletonMap("Invoice", Invoice.class.getCanonicalName()));
                    DataFormat bindy = new BindyCsvDataFormat(Invoice.class);
                    from("file://TEST?fileName=input.csv&noop=true")
                            .unmarshal(bindy)

                            .marshal()
                            .xstream()
                            .to("file://TEST?fileName=test.csv-${date:now:yyyyMMddHHmmssSSSSS}" + ".xml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
