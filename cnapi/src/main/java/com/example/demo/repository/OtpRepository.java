package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.Otp;

@Repository 	
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
public interface OtpRepository extends MongoRepository<Otp, String> {

	public Otp findById(int id);
	public List<Otp> findByUserId(String userId);
	


	
}
