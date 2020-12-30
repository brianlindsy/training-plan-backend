package com.trainingplans.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trainingplans.entities.Review;

@Component
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
