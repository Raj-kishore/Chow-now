package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.model.Dish;

@Repository 	
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
public interface DishRepository extends MongoRepository<Dish, String> { //Dish DAO

	public List<Dish> findByUId(String id);

	public List<Dish> findAllBy(TextCriteria criteria);
	
	public List<Dish> findAllByIsVeg();
	
	public List<Dish> findAllByDelivery();

}
