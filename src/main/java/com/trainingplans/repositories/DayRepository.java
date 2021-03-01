package com.trainingplans.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trainingplans.entities.user.trainingplan.Day;

@Component
public interface DayRepository extends CrudRepository<Day, Long> {
}
