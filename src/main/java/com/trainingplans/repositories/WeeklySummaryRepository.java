package com.trainingplans.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trainingplans.entities.WeeklySummary;

@Component
public interface WeeklySummaryRepository extends CrudRepository<WeeklySummary, Long> {
}
