package com.example.csvinxml.processor.app.transformation;

import com.example.csvinxml.processor.app.ConvertorRoute;
import com.example.csvinxml.processor.app.transformation.csv.CsvToPersonTransformer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.IntStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import junit.framework.Assert;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

import static com.example.csvinxml.processor.app.transformation.CsvToXmlTransformerTest.compressByteArrayEncodeToString;
import static junit.framework.Assert.assertEquals;

public class CsvToPersonTransformerTest {
    private CsvToPersonTransformer transformer;

    @Before
    public void setup(){
        transformer = new CsvToPersonTransformer();
    }

    @Test
    public void convert(){
        List<String> csvRow = Arrays.asList("john", "smith");
        List<Person> result = transformer.convert(Arrays.asList(csvRow));

        assertEquals(result.size(), 1);

        Person person = result.get(0);

        assertEquals(person.getLastName(), "smith");
        assertEquals(person.getFirstName(), "john");
    }
    @Test
    public void assertOneLineAndImageFromFile() throws IOException, ParseException {
        byte[] bytes = new ClassPathResource("files/img.txt").getInputStream().readAllBytes();
        String invoiceImg = compressByteArrayEncodeToString(bytes);
        List<String> csvRow = Arrays.asList("South African Gold Mines Corp", "scanned_invoice_1.png", invoiceImg,"2020-09-01",
                "AA16789-1","22000.89", "USD","NEW","Goldie & Sons\nSouth African Gold Mines Corp");
        List<Invoice> result = transformer.convertInvoice(Arrays.asList(csvRow));

        assertEquals(result.size(), 1);

        Invoice invoice = result.get(0);

        Assert.assertEquals(invoice.getBuyerName(), "South African Gold Mines Corp");
        Assert.assertEquals(invoice.getImageName(), "scanned_invoice_1.png");
        Assert.assertEquals(invoice.getInvoiceImage(), invoiceImg);
        Assert.assertEquals(invoice.getInvoiceDueDate(), "2020-09-01");
        Assert.assertEquals(invoice.getInvoiceNumber(), "AA16789-1");
        Assert.assertEquals(invoice.getInvoiceAmount(),"22000.89");
        Assert.assertEquals(invoice.getInvoiceCurrency(), "USD");
        Assert.assertEquals(invoice.getInvoiceStatus(), "NEW");
        Assert.assertEquals(invoice.getSupplier(), "Goldie & Sons\nSouth African Gold Mines Corp");
    }
    @Test
    public void readCsvAssertFirstLine() throws IOException, ParseException {
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(new ClassPathResource("files/input.csv").getFile()));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        List<Invoice> result = transformer.convertInvoice(records);

        assertEquals(result.size(), 11);

        Invoice invoice = result.get(1);

        Assert.assertEquals(invoice.getBuyerName(), "South African Gold Mines Corp");
        Assert.assertEquals(invoice.getImageName(), "scanned_invoice_1.png");
        Assert.assertEquals(invoice.getInvoiceDueDate(), "2020-09-01");
        Assert.assertEquals(invoice.getInvoiceNumber(), "AA16789-1");
        Assert.assertEquals(invoice.getInvoiceAmount(),"22000.89");
        Assert.assertEquals(invoice.getInvoiceCurrency(), "USD");
        Assert.assertEquals(invoice.getInvoiceStatus(), "NEW");
    }
    @Test
    public void iterateCsvAndSplitToXmlFiles() throws Exception {
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(new ClassPathResource("files/input.csv").getFile()));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        //input lines in foreach for separate files?
        List<Invoice> result = transformer.convertInvoice(records);
        assertEquals(result.size(), 11);
        for(int i=1; i<result.size();i++){
            Invoice splittedElement = result.get(i);
            splittedElement.setInvoiceImage(null);
            JAXBContext jaxbContext = JAXBContext.newInstance(Invoice.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Path pathToFile = Paths.get("C:/workspace/splittedCSV/"+i+"splitRecord"+new Date().getTime()+".xml");
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);

            File file = new File(pathToFile.toFile().getPath());
            jaxbMarshaller.marshal(splittedElement, file);
        }

    }

}
