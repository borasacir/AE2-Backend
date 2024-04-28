package com.appliedenergetics2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.appliedenergetics2.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
}
