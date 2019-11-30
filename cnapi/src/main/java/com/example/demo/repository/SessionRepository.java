package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.Session;
import com.example.demo.model.User;

@Repository 	
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
public interface SessionRepository extends MongoRepository<Session, String> {

	public Session findById(int id);
	public List<Session> findByuserId(String userId);
	public List<Session> findByAccessToken(String accessToken);
	
}
