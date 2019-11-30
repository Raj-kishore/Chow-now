package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Blacklist;
import com.example.demo.model.User;
import com.example.demo.service.BlacklistService;
import com.example.demo.service.PersonService;
import com.example.demo.service.UserService;

@RestController

public class BlacklistController {

	@Autowired
	private BlacklistService blacklistService;

//	@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8080", "http://108.179.222.240:8100","https://phorons.com","https://phorons.com:80"})
	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/blacklistPerson", method = RequestMethod.POST)
	public String create(@RequestBody(required = false) Blacklist blacklist) {
		Blacklist b = blacklistService.create(blacklist);
		return b.toString();
	}

	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/getAllblacklisted", method = RequestMethod.GET)
	public List<Blacklist> getAll() {
		return blacklistService.getAll();
	}

	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/getBlacklistById", method = RequestMethod.POST)
	public Optional<Blacklist> findById(@RequestBody(required = true) String id) {
		return blacklistService.findById(id);
	}

//	@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8080", "http://108.179.222.240:8100" })
//	@RequestMapping(value = "/deleteAllUsers", method = RequestMethod.DELETE)
//	public String deleteAll() {
//		blacklistService.deleteAll();
//		return "Deleted all records";
//	}

}
//[1] : https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-mongodb-crud-operations-example/