package com.trainingplans.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trainingplans.entities.user.User;

@Component
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
