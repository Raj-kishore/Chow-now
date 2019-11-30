package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.Dish;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;

@Repository
@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8080" })
public interface TransactionDao extends MongoRepository<Transaction, String> {
	public Transaction findById(int id);
	public Transaction findByUserIdAndCookIdAndDishId(String userId, String cookId, String dishId);
	
	public List<Transaction> findByUserId(String id);
}
