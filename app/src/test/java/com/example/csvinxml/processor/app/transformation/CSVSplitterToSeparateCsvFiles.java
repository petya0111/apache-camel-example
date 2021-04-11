package com.example.csvinxml.processor.app.transformation;

import com.example.csvinxml.processor.app.transformation.csv.CsvToPersonTransformer;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import static junit.framework.Assert.assertEquals;

/**
 * @author Petya Marinova
 */
public class CSVSplitterToSeparateCsvFiles {
    private CsvToPersonTransformer transformer;

    @Before
    public void setup() {
        transformer = new CsvToPersonTransformer();
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
        Collections.sort(result);
        assertEquals(result.size(), 11);
        for (int i = 1; i < result.size(); i++) {
            Invoice splittedElement = result.get(i);
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(Invoice.class);
            schema = schema.withColumnSeparator(',');
            Path pathToFile = Paths.get("C:/workspace/splittedCSV/" + i +splittedElement.getBuyerName()+ "CSVsplitRecord " + splittedElement.getInvoiceDueDate()+new Date().getTime() + ".csv");
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);


            // output writer
            ObjectWriter myObjectWriter = mapper.writer(schema);
            File tempFile = new File(pathToFile.toFile().getPath());
            FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
            OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            myObjectWriter.writeValue(writerOutputStream, result.get(i));
        }

    }
}
