package com.appliedenergetics2.repository;

import com.appliedenergetics2.model.Modpacks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModpackRepository extends MongoRepository<Modpacks, String> {
	@Query("{'items.title': {$regex: ?0, $options: 'i'}}")  //{?0, $options: 'i'}
	List<Modpacks> findByItemTitleContaining(String title);

	@Query("{'items._id': ?0}")
    Optional<Modpacks> findModpackContainingItemId(String itemId);
	
	@Query("{'id': ?0}")
    Optional<Modpacks> findTypeContainingItemId(String itemId);
}

