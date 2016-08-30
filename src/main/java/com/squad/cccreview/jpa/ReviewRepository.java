package com.squad.cccreview.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.squad.cccreview.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

}
