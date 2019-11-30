package com.example.demo.service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
   
	@Autowired
	private UserRepository userRepository;
	
	//Create operation
	public User create(User user) {
		return userRepository.save(user);
	}
	
	//Retrieve operation
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	//Retrieve by phone no
	public Optional<User> findByPhoneNo(String phoneNo){
		Optional<User> userObj = null;
	try {	
		userObj = userRepository.findByPhoneNo(phoneNo);
	}catch(IncorrectResultSizeDataAccessException e) {
		e.printStackTrace();
	}
	return userObj;
	}
	
	public User findByPhonenoAndPass(String phoneNo, String pass) {
		return userRepository.findByphoneNoAndPass(phoneNo, pass);
	}
	
	//Retrieve by id
	public Optional<User> findById(String id){
		return userRepository.findById(id);
	}
	
//	//Update operation
//	public User update(int id, String phoneNo) {
//		User u = userRepository.findById(id);
//		u.setPhoneNo(phoneNo);
//		return userRepository.save(u);
//	}
	
	//Update operation
	public User update(User user) {
		return userRepository.save(user);
	}
	
	//Delete operation
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
//	public void delete(String phone) {
//
//		userRepository.delete(userRepository.findByPhoneNo(phone));
//	}

}