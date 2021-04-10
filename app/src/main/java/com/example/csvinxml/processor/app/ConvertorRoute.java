package com.example.csvinxml.processor.app;

import com.example.csvinxml.processor.app.transformation.Invoice;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Petya Marinova
 */
public class ConvertorRoute implements RoutesBuilder {


    @Override
    public void addRoutesToCamelContext(CamelContext context) throws Exception {
        context.addRoutes(new RouteBuilder() {
            public void configure() {

                try {
                    DataFormat bindy = new BindyCsvDataFormat(com.example.csvinxml.processor.app.transformation.Invoice.class);
                    from("file://TEST?fileName=input.csv")
                            .unmarshal(bindy)
                            .marshal()
                            .xstream()
                            .to("file://TEST?fileName=output.xml");
////                            it default to C:/ in Windows
//                            to("file://Users/pmarinova/Desktop/employee.xml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
