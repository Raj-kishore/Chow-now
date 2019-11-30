package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Dish;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.TransactionDao;

@Service
public class TransactionService {

	@Autowired
	private TransactionDao transactiondao;

	// Create operation
	public Transaction create(Transaction transaction) {
		return transactiondao.save(transaction);
	}

	// Retrieve operation
	public List<Transaction> getAll() {
		return transactiondao.findAll();
	}

	// Retrieve by id
	public String find(String userId, String cookId, String dishId) {
		Transaction t =  transactiondao.findByUserIdAndCookIdAndDishId(userId, cookId, dishId);
		String id = t.getId();
		return id;
	}

	// Delete operation
	public void deleteAll() {
		transactiondao.deleteAll();
	}
	
	// Retrieve by id
		public Optional<Transaction> findById(String id) {
			return transactiondao.findById(id);
		}

		// Retrieve by list id
		public List<Transaction> findListById(String id) {
			return transactiondao.findByUserId(id);
		}

	public void delete(String id) {
//		Optional<Transaction> t = transactiondao.findById(id);
		transactiondao.deleteById(id);
	}
	
	
	// Update operation
	public Transaction update(Transaction trans) {
		return transactiondao.save(trans);
	}
}