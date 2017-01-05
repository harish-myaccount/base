package com.squad.cccreview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Review {

	public Review(String writeup, double rating) {
		this.writeup = writeup;
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWriteup() {
		return writeup;
	}

	public void setWriteup(String writeup) {
		this.writeup = writeup;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getInvoiceImageUrl() {
		return invoiceImageUrl;
	}

	public void setInvoiceImageUrl(String invoiceImageUrl) {
		this.invoiceImageUrl = invoiceImageUrl;
	}

	public Review() {
	}

	@Id
	String id;

	String writeup;
	Double rating;
	String company;
	String location;
	String billId;
	String invoiceImageUrl;

}
