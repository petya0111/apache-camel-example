package com.example.csvinxml.processor.app.camelgem;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord()
public class OrderHeader {

    @DataField(pos = 1, delimiter = "\t")
    private int id;

    @DataField(pos = 2, delimiter = "\t")
    private String name;

    @DataField(pos = 3, delimiter = "\t", pattern = "yyyy-MM-dd")
    private Date date1;

    @DataField(pos = 4, delimiter = "\t", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date2;

    @DataField(pos = 5, length = 1)
    private int numerOfFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public int getNumerOfFile() {
        return numerOfFile;
    }

    public void setNumerOfFile(int numerOfFile) {
        this.numerOfFile = numerOfFile;
    }

}