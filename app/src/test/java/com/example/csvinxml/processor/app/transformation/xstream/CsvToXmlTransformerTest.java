package com.example.csvinxml.processor.app.transformation.xstream;

import com.example.csvinxml.processor.app.transformation.Person;
import com.thoughtworks.xstream.XStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.zip.GZIPOutputStream;
import junit.framework.Assert;
import com.example.csvinxml.processor.app.transformation.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.codec.binary.Base64;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CsvToXmlTransformerTest {
    private CsvToXmlTransformer transformer;
    XStream xstream;
    @Value("files/input.csv")
    private Resource inputCsv;
    @Value("files/img.txt")
    private Resource testImg;

    @Before
    public void setup(){
        transformer = new CsvToXmlTransformer();
        xstream = new XStream();
        transformer.setXstream(xstream);
    }

    @Test
    public void convert(){
        List<String> csvRow = Arrays.asList("john", "smith");
        List<String> result = transformer.convert(Arrays.asList(csvRow));

        Assert.assertEquals(result.size(), 1);

        Person person = (Person) xstream.fromXML(result.get(0));

        Assert.assertEquals(person.getLastName(), "smith");
        Assert.assertEquals(person.getFirstName(), "john");
    }
    @Test
    public void convertInvoice() throws IOException {
        byte[] bytes = new ClassPathResource("files/img.txt").getInputStream().readAllBytes();
        String invoiceImg = compressByteArrayEncodeToString(bytes);
        List<String> csvRow = Arrays.asList("South African Gold Mines Corp", "scanned_invoice_1.png", invoiceImg,"2020-09-01",
                "AA16789-1","22000.89", "USD","NEW","Goldie & Sons\nSouth African Gold Mines Corp");
        List<String> result = transformer.convertInvoice(Collections.singletonList(csvRow));
        Assert.assertEquals(result.size(), 1);

        Invoice invoice = (Invoice) xstream.fromXML(result.get(0));
        Assert.assertEquals(invoice.getBuyerName(), "South African Gold Mines Corp");
        Assert.assertEquals(invoice.getImageName(), "scanned_invoice_1.png");
        Assert.assertEquals(invoice.getInvoiceImage(), invoiceImg);
        Assert.assertEquals(invoice.getInvoiceDueDate(), "2020-09-01");
        Assert.assertEquals(invoice.getInvoiceNumber(), "AA16789-1");
        Assert.assertEquals(invoice.getInvoiceAmount(), Double.parseDouble("22000.89"));
        Assert.assertEquals(invoice.getInvoiceCurrency(), "USD");
        Assert.assertEquals(invoice.getInvoiceStatus(), "NEW");
        Assert.assertEquals(invoice.getSupplier(), "Goldie & Sons\nSouth African Gold Mines Corp");

    }
    public static String compressByteArrayEncodeToString(byte[] srcBytes)  throws IOException {
        if (srcBytes != null) {
//for reference https://lifelongprogrammer.blogspot.com/2013/11/java-use-zip-stream-and-base64-to-compress-big-string.html
            ByteArrayOutputStream rstBao = new ByteArrayOutputStream();
            GZIPOutputStream zos = new GZIPOutputStream(rstBao);
            try {
                zos.write(srcBytes);
                byte[] bytes = rstBao.toByteArray();
                return Base64.encodeBase64String(bytes);
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                zos.close();
                rstBao.close();
            }
        }
        return null;
    }

}
