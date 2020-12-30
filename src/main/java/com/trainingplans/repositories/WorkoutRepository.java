package com.trainingplans.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trainingplans.entities.Workout;

@Component
public interface WorkoutRepository extends CrudRepository<Workout, Long> {
}
