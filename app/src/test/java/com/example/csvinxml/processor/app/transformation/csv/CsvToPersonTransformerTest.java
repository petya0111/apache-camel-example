package com.example.csvinxml.processor.app.transformation.csv;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import junit.framework.Assert;
import com.example.csvinxml.processor.app.transformation.Invoice;
import com.example.csvinxml.processor.app.transformation.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

import static com.example.csvinxml.processor.app.transformation.xstream.CsvToXmlTransformerTest.compressByteArrayEncodeToString;
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
    public void convertCsvToPojo() throws IOException, ParseException {
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
        Assert.assertEquals(invoice.getInvoiceDueDate(), new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-01"));
        Assert.assertEquals(invoice.getInvoiceNumber(), "AA16789-1");
        Assert.assertEquals(invoice.getInvoiceAmount(), Double.parseDouble("22000.89"));
        Assert.assertEquals(invoice.getInvoiceCurrency(), "USD");
        Assert.assertEquals(invoice.getInvoiceStatus(), "NEW");
        Assert.assertEquals(invoice.getSupplier(), "Goldie & Sons\nSouth African Gold Mines Corp");
    }

}
