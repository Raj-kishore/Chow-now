package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Session;
import com.example.demo.repository.SessionRepository;

@Service
public class SessionService {
   
	@Autowired
	private SessionRepository sessionRepository;
	
	//Create operation
	public Session create(Session session) {
		return sessionRepository.save(session);
	}
	
	//Retrieve operation
	public List<Session> getAll(){
		return sessionRepository.findAll();
	}
	
	//Retrieve by id
	public List<Session> findByAccessToken(String accessToken){
		return sessionRepository.findByAccessToken(accessToken);
	}
	
	//Delete operation
	public void deleteAll() {
		sessionRepository.deleteAll();	
	}

	public void deleteByuId() {
		// TODO Auto-generated method stub
		
	}

}