package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Session;
import com.example.demo.model.User;
import com.example.demo.service.SessionService;

@RestController

public class SessionController {

	@Autowired
	private SessionService sessionService;
	
	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/createSession", method = RequestMethod.POST)
	public String create(@RequestBody(required = false) Session session ) {
		Session s = sessionService.create(session);
		return s.toString();
	}
	
	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/getSessionByAccessToken", method = RequestMethod.GET)
	public List<Session> findByAccessId(@RequestParam(required = true) String accessToken){
		List<Session> list =  sessionService.findByAccessToken(accessToken);
		if (list.size() == 0) {
			list = null;
		}
		return list;	
	}
	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/getAllsessions", method = RequestMethod.GET)
	public List<Session> getAll(){
		return sessionService.getAll();
	}	
	
	@CrossOrigin(origins = {"*"})
	@RequestMapping (value = "/deleteAllSession", method = RequestMethod.DELETE)
	public String deleteAll() {
		sessionService.deleteAll();
		return "Deleted all records";
	}
	

	
	
	
}