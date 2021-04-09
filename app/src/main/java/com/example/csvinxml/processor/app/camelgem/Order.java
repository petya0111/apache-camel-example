package com.example.csvinxml.processor.app.camelgem;

import java.math.BigDecimal;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord(header = OrderHeader.class)
public class Order {

    @DataField(pos = 1, delimiter = "\t")
    private int lp;

    @DataField(pos = 2, delimiter = "\t")
    private int id;

    @DataField(pos = 3, delimiter = "\t")
    private BigDecimal priceByOne;

    @DataField(pos = 4, delimiter = "\t")
    private BigDecimal count;

    @DataField(pos = 5, length = 2)
    private int foreignId;

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPriceByOne() {
        return priceByOne;
    }

    public void setPriceByOne(BigDecimal priceByOne) {
        this.priceByOne = priceByOne;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public int getForeignId() {
        return foreignId;
    }

    public void setForeignId(int foreignId) {
        this.foreignId = foreignId;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("...lp:").append(lp).append("\n");
        str.append("...id:").append(id).append("\n");
        str.append("...priceByOne:").append(priceByOne).append("\n");
        str.append("...count:").append(count).append("\n");
        str.append("...foreignId:").append(foreignId).append("\n");
        return str.toString();
    }
}