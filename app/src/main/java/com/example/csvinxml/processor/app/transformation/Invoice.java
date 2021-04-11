package com.example.csvinxml.processor.app.transformation;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( propOrder = { "buyerName", "imageName", "invoiceImage", "invoiceDueDate", "invoiceNumber", "invoiceAmount", "invoiceCurrency", "invoiceStatus","supplier" })
@XmlRootElement(name = "Invoice")
@CsvRecord(separator = ",",skipFirstLine = true) //skipFirstLine
@XStreamAlias("InvoiceRoot")
public class Invoice implements Comparable<Invoice> {


	@XmlAttribute(name = "buyer", required = true)
	@DataField(pos = 1)
	@XStreamAsAttribute
	@XStreamAlias("buyerName:buyer_name")
	private String buyerName;

	@XmlAttribute(name = "image_name", required = true)
	@DataField(pos = 2)
	@XStreamImplicit(itemFieldName = "myField")
	private String imageName;

	@XmlAttribute(name = "invoice_image", required = true)
	@DataField(pos = 3)
//	@IgnoreForToString
	private String invoiceImage;

	@XmlAttribute(name = "invoice_due_date", required = true)
//	@DataField(pos = 4)
	@DataField(pos = 4, pattern = "yyyy-MM-dd")
	private String invoiceDueDate;

	@XmlAttribute(name = "invoice_number", required = true)
	@DataField(pos = 5)
	private String invoiceNumber;

	@XmlAttribute(name = "invoice_amount", required = true)
	@DataField(pos = 6)
	private String invoiceAmount;

	@XmlAttribute(name = "invoice_currency", required = true)
	@DataField(pos = 7)
	private String invoiceCurrency;

	@XmlAttribute(name = "invoice_status", required = true)
	@DataField(pos = 8)
	private String invoiceStatus;

	@XmlAttribute(name = "supplier", required = true)
	@DataField(pos = 9)
	private String supplier;

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getInvoiceImage() {
		return invoiceImage;
	}

	public void setInvoiceImage(String invoiceImage) {
		this.invoiceImage = invoiceImage;
	}

	public String getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(String invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceCurrency() {
		return invoiceCurrency;
	}

	public void setInvoiceCurrency(String invoiceCurrency) {
		this.invoiceCurrency = invoiceCurrency;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	@Override
	public int compareTo(Invoice invoice) {
		if (getBuyerName() == null || invoice.getBuyerName() == null) {
			return 0;
		}
		return getBuyerName().compareTo(invoice.getBuyerName());
	}
	public Invoice() {
	}
// all args, getters, setters tostring


}
