package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Dish;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.service.DishService;
import com.example.demo.service.TransactionService;

@RestController

public class TransactionController {

	@Autowired
	private TransactionService transactionservice;

	@CrossOrigin(origins = { "*" })
	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public String create(@RequestBody(required = false) Transaction transaction) {
		Transaction t = transactionservice.create(transaction);
		return t.toString();
	}

	@CrossOrigin(origins = { "*" })
	@RequestMapping(value = "/getTrans", method = RequestMethod.GET)
	public String getTrans(@RequestParam String userId, String cookId, String dishId) {
		String id = "";
		try {
			id = transactionservice.find(userId, cookId, dishId);
		} catch (NullPointerException e) {
			id = null;
		}
		return "" + id;	
	}

	@CrossOrigin(origins = { "*" })
	@RequestMapping(value = "/getListTransById", method = RequestMethod.GET)
	public List<Transaction> getTransbyId(@RequestParam String userId) {
		List<Transaction> t = transactionservice.findListById(userId);
		return t;
	}

	@CrossOrigin(origins = { "*" })
	@RequestMapping(value = "/getAlltrans", method = RequestMethod.GET)
	public List<Transaction> getAll() {
		return transactionservice.getAll();
	}

	@CrossOrigin(origins = { "*" })
	@RequestMapping(value = "/updateTransById", method = RequestMethod.PUT)
	public String update(@RequestParam String id, @RequestBody(required = false) Transaction trans) {
		Optional<Transaction> internaOptionallTransactioon = transactionservice.findById(id);
		Transaction internalTransaction = internaOptionallTransactioon.get();
		String tid = internalTransaction.getId();
		String userId = internalTransaction.getUserId();
		String cookId = internalTransaction.getCookId();
		String dishId = internalTransaction.getDishId();
		LocalDateTime timer = internalTransaction.getRequestTime();

		trans.setId(tid);
		trans.setUserId(userId);
		trans.setCookId(cookId);
		trans.setDishId(dishId);
		trans.setRequestTime(timer);

		Transaction t = transactionservice.update(trans);

		return t.toString();
	}

	@CrossOrigin(origins = { "*" })
	@RequestMapping(value = "/deleteTransById", method = RequestMethod.DELETE)
	public String delete(@RequestParam String userId, String cookId, String dishId) {
		String id = transactionservice.find(userId, cookId, dishId);
		transactionservice.delete(id);
		return "Deleted " + id;
	}

//	@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8080" , "http://108.179.222.240:8100"})
//	@RequestMapping(value = "/deleteUserByTransId", method = RequestMethod.DELETE)
//	public String delete(@RequestParam String tid) {
//		transactionservice.delete(tid);
//		return "Deleted "+tid;
//	}

}
//[1] : https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-mongodb-crud-operations-example/