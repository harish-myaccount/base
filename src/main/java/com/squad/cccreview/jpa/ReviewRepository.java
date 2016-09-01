package com.squad.cccreview.jpa;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.squad.cccreview.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

	@Query(fields = "{ 'invoiceImageUrl' : 1}")
	List<Review> findByBillIdIsNull();
	
	@Query(value = "{ $or: [{'writeup': {$regex : ?0, $options: 'i'}},{'company': {$regex : ?0, $options: 'i'}}]}")
	List<Review> findByBillIdNotNullAndRegex(String regexString);

}
