package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import com.example.demo.model.Dish;
import com.example.demo.model.User;
import com.example.demo.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;

	// Create operation
	public Dish create(Dish dish) {
		return dishRepository.save(dish);
	}

	// Retrieve operation
	public List<Dish> getAll() {
		return dishRepository.findAll();
	}
	
	// Retrieve operation
	public List<Dish> getAllByVeg() {
		return dishRepository.findAllByIsVeg();
	}
	
	// Retrieve operation
	public List<Dish> getAllByDelivery() {
		return dishRepository.findAllByDelivery();
	}

	// Retrieve by id
	public Optional<Dish> findById(String id) {
		return dishRepository.findById(id);
	}

	// Retrieve by uid
	public List<Dish> findByuId(String uid) {
		return dishRepository.findByUId(uid);
	}

	// Update operation
	public Dish update(Dish dish) {
		return dishRepository.save(dish);
	}

	// Delete operation
	public void deleteAll() {
		dishRepository.deleteAll();
	}

	public List<Dish> findBySearch(String q) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(q);
		//Query query = TextQuery.queryText(criteria).sortByScore().with(new PageRequest(0, 5));
		List<Dish> recipes = dishRepository.findAllBy(criteria);

		return recipes;

	}

}