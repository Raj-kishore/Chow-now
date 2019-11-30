package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Otp;
import com.example.demo.model.Session;
import com.example.demo.model.User;
import com.example.demo.repository.OtpRepository;
import com.example.demo.repository.SessionRepository;

@Service
public class OtpService {
   
	@Autowired
	private OtpRepository otpRepository;
	
	//Create operation
	public Otp create(Otp otp) {
		return otpRepository.save(otp);
	}
	
	//Retrieve operation
	public List<Otp> getAll(){
		return otpRepository.findAll();
	}
	
	//Retrieve by id
	public List<Otp> findByUserId(String userId){
		return otpRepository.findByUserId(userId);
	}
	
	//Delete operation
	public void deleteAll() {
		otpRepository.deleteAll();	
	}

	

	public void delete(String id) {
		// TODO Auto-generated method stub
		
		List<Otp> o = otpRepository.findByUserId(id);
		if (o.get(0) != null) {
			otpRepository.delete(o.get(0));
		}
	}

	



}