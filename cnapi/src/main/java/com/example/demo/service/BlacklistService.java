package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Blacklist;
import com.example.demo.model.Dish;
import com.example.demo.model.User;
import com.example.demo.repository.BlacklistDao;
import com.example.demo.repository.DishRepository;

@Service
public class BlacklistService {
   
	@Autowired
	private BlacklistDao blacklistdao;
	
	//Create operation
	public Blacklist create(Blacklist blacklist) {
		return blacklistdao.save(blacklist);
	}
	
	//Retrieve operation
	public List<Blacklist> getAll(){
		return blacklistdao.findAll();
	}
	
	//Retrieve by id
	public Optional<Blacklist> findById(String id){
		return blacklistdao.findById(id);
	}


	
	//Delete operation
	public void deleteAll() {
		blacklistdao.deleteAll();	
	}





}