package com.squad.cccreview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Review {

	public Review(String writeup, String company, String location, double rating) {
		this.company = company;
		this.writeup = writeup;
		this.rating = rating;
		this.location = location;
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
