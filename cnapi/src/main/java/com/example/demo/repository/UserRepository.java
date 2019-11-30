package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.User;

@Repository 	
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
public interface UserRepository extends MongoRepository<User, String> {

	//public User findById(int id);
	//public Optional<User> findById(String id);
	public Optional<User> findByPhoneNo(String phoneNo);
	public User findByphoneNoAndPass(String phoneNo, String pass);
//	public void delete(Optional<User> u);
	

	
}
