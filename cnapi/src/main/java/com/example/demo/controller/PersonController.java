package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RestController

public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
		Person p = personService.create(firstName, lastName, age);
		return p.toString();
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Person getPerson(@RequestParam String firstName) {
		return personService.getByFirstName(firstName);
	}
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Person> getAll(){
		return personService.getAll();
	}
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
		Person p = personService.update(firstName, lastName, age);
		return p.toString();
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam String firstName) {
		personService.delete(firstName);
		return "Deleted "+firstName;
	}
	@RequestMapping (value = "/deleteAll", method = RequestMethod.DELETE)
	public String deleteAll() {
		personService.deleteAll();
		return "Deleted all records";
	}
	
}