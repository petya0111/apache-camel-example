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
//                    DataFormat bindy = new BindyCsvDataFormat(Invoice.class);
//                    from("file://TEST?fileName=input.csv")
//                            .unmarshal(bindy)
//                            .marshal()
//                            .xstream()
//                            .to("jms:queue:outputtttt.xml");
//from("file://C:/workspace/input.csv").
//                    from("file:"+new ClassPathResource("files/input.csv").getPath()).
////                    from("file://files/input.csv").
//                            unmarshal(new BindyCsvDataFormat(Invoice.class)).
//                            marshal().
//                            xstream().
////        to("file://C:/workspace/output.xml");
//                            to("file:"+new ClassPathResource("files/output.xml").getPath());
                    DataFormat bindy = new BindyCsvDataFormat(Invoice.class);
                    from("file://workspace/input.csv").
                            unmarshal(bindy).
                            marshal().
                            xstream().
//                            it default to C:/ in Windows
                            to("file://Users/pmarinova/Desktop/employee.xml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
