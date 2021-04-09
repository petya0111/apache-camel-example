package com.example.csvinxml.processor.app.camelgem;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlOrder extends OrderHeader {

    public XmlOrder() {
    }

    private List<Order> line;

    public XmlOrder(OrderHeader header, List<Order> lines) {
        this.setDate1(header.getDate1());
        this.setDate2(header.getDate2());
        this.setId(header.getId());
        this.setName(header.getName());
        this.setNumerOfFile(header.getNumerOfFile());
        this.setLines(lines);
    }

    public List<Order> getLines() {
        return line;
    }

    public void setLines(List<Order> lines) {
        this.line = lines;
    }

}