package com.example.demo.controller;

import java.util.List;

import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;
import com.example.demo.service.PersonService;
import com.example.demo.service.UserService;
import java.io.IOException;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.User;
import com.example.demo.service.PersonService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "https://phorons.com", allowedHeaders = "*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User create(@RequestBody(required = false) User user) {
		User u = userService.create(user);
		return u;
	}

	@RequestMapping(value = "/mmiauth", method = RequestMethod.GET)
	public ResponseEntity<String> mmioauth(@RequestParam(required = true) String query) throws IOException {

		RestTemplate restTemplate = new RestTemplate();
		final String url = "https://outpost.mapmyindia.com/api/security/oauth/token";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "client_credentials");
		map.add("client_id",
				"4i2v8_ss3s_XRGl9aMwWWm54Q6NfV8hNerKgfhr8e9GKTe8sxle58v2-EZKdEo9el8f4U3vgS55tybCETRzLwg==");
		map.add("client_secret",
				"kCZgyC2hGNJTeHsx4RbtdQNJIPEovU9vNi0WwuZ0yO-A3bpHCDaowWjNAiIbZ1E3gnnW8jxO8HPNSSr-cJihgzyiofe0MMSv");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		// response ye he.. isme scope ka balue he, yaha se scope retrieve karke config
		// me use kese kare
		System.out.println("MMI response" + response);

		// Get the Access Token From the recieved JSON response
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		String token = node.path("access_token").asText();

		String url1 = "https://atlas.mapmyindia.com/api/places/search/json?query=" + query;

		// Use the access token for authentication
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers1);

		ResponseEntity<String> responseofmmi = restTemplate.exchange(url1, HttpMethod.GET, entity, String.class);

		return responseofmmi;
	}

	@RequestMapping(value = "/getAllusers", method = RequestMethod.GET)
	public List<User> getAll() {
		return userService.getAll();
	}

	@RequestMapping(value = "/getUserByPhone", method = RequestMethod.GET)
	public Optional<User> findByPhoneNo(@RequestParam(required = true) String phoneNo) {
		return userService.findByPhoneNo(phoneNo);
	}

//	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
//	public Optional<User> findById(@RequestParam(required = true) String id) {
//
//		return userService.findById(id);
//	}

	@RequestMapping(value = "/getUserByPhonePass", method = RequestMethod.POST)
	public User findByPhonePass(@RequestBody(required = true) String data) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode studentNode;
		String phno = null;
		String pass = null;
		
		try {
			
			studentNode = mapper.readTree(data);
			phno = studentNode.get("phno").asText();
			pass = studentNode.get("pass").asText();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}

		return userService.findByPhonenoAndPass(phno, pass);
	}

	@RequestMapping(value = "/updateUserFirstTimeinfoById", method = RequestMethod.PUT)
	public String updatefi(@RequestParam String phoneno, @RequestBody(required = false) User user) {
		// Reference [1]
		Optional<User> internaOptionallUser = userService.findByPhoneNo(phoneno);
		User internalUser = internaOptionallUser.get();
		String phnoe = internalUser.getPhoneNo();
		String cookName = internalUser.getCookName();
		Boolean isVeg = internalUser.getIsVeg();
		List<String> cuisines = internalUser.getCuisines();
		Boolean isfirstTime = user.getFirstTime();
		Boolean isCook = user.getIsCook();
		
		user.setPhoneNo(phnoe);
		user.setCookName(cookName);
		user.setIsVeg(isVeg);
		user.setCuisines(cuisines);
		user.setFirstTime(isfirstTime);
		user.setIsCook(isCook);

		User u = userService.update(user);
		return u.toString();
	}

	@RequestMapping(value = "/updateUserChefinfoById", method = RequestMethod.PUT)
	public String update(@RequestParam String phoneno, @RequestBody(required = false) User user) {
		// Reference [1]
		Optional<User> internaOptionallUser = userService.findById(phoneno);
		User internalUser = internaOptionallUser.get();
		String phnoe = internalUser.getPhoneNo();
		user.setPhoneNo(phnoe);
		user.setFirstTime(internalUser.getFirstTime());
		User u = userService.update(user);
		return u.toString();
	}

	@RequestMapping(value = "/updateUserVerfById", method = RequestMethod.PUT)
	public String updateUserVerf(@RequestParam String id, @RequestBody(required = false) User user) {
		// Reference [1]
		Optional<User> internaOptionallUser = userService.findByPhoneNo(id);
		User internalUser = internaOptionallUser.get();
		
		String phone = internalUser.getPhoneNo();
		String cookName = internalUser.getCookName();
		Boolean isVeg = internalUser.getIsVeg();
		List<String> cuisines = internalUser.getCuisines();
		Boolean isfirstTime = internalUser.getFirstTime();
		String email = internalUser.getEmail();
		String pass = internalUser.getPass();
		Double lat = internalUser.getLat();
		Double lng = internalUser.getLng(); 
		Boolean isPro = internalUser.getIsPro();
		Boolean isCook = internalUser.getIsCook();

		user.setPhoneNo(phone);
		user.setCookName(cookName);
		user.setIsVeg(isVeg);
		user.setCuisines(cuisines);
		user.setFirstTime(isfirstTime);
		user.setEmail(email);
		user.setPass(pass);
		user.setLat(lat);
		user.setLng(lng);
		user.setIsPro(isPro);
		user.setIsCook(isCook);
		
		User u = userService.update(user);
		return u.toString();
	}
	
	
	@RequestMapping(value = "/updateUserPassById", method = RequestMethod.PUT)
	public String updateUserPass(@RequestParam String id, @RequestBody(required = false) User user) {
		// Reference [1]
		Optional<User> internaOptionallUser = userService.findByPhoneNo(id);
		User internalUser = internaOptionallUser.get();
		
		String phone = internalUser.getPhoneNo();
		String cookName = internalUser.getCookName();
		Boolean isVeg = internalUser.getIsVeg();
		List<String> cuisines = internalUser.getCuisines();
		Boolean isfirstTime = internalUser.getFirstTime();
		String email = internalUser.getEmail();
		String verf = internalUser.getIsVerified();
		Double lat = internalUser.getLat();
		Double lng = internalUser.getLng(); 
		Boolean isPro = internalUser.getIsPro();
		Boolean isCook = internalUser.getIsCook();

		user.setPhoneNo(phone);
		user.setCookName(cookName);
		user.setIsVeg(isVeg);
		user.setCuisines(cuisines);
		user.setFirstTime(isfirstTime);
		user.setEmail(email);
		user.setIsVerified(verf);
		user.setLat(lat);
		user.setLng(lng);
		user.setIsPro(isPro);
		user.setIsCook(isCook);
		
		User u = userService.update(user);
		return u.toString();
	}


//	@RequestMapping(value = "/deleteUserById", method = RequestMethod.DELETE)
//	public String delete(@RequestParam int id) {
//		userService.delete(id);
//		return "Deleted "+id;
//	}

	@RequestMapping(value = "/getMapData", method = RequestMethod.GET)
	public String getMapData(@RequestParam String lat, String lng) {
		final String uri = "https://apis.mapmyindia.com/advancedmaps/v1/o5jls9cv4d81jihcipb3livmyedygsl4/rev_geocode?lat="
				+ lat + "&lng=" + lng;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}

	@RequestMapping(value = "/getAutoMapData", method = RequestMethod.GET)
	public String getAutoMapData(@RequestParam String autocomplete) {
		final String uri = "https://atlas.mapmyindia.com/api/places/search/json?query=" + autocomplete;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}

	@RequestMapping(value = "/deleteAllUsers", method = RequestMethod.DELETE)
	public String deleteAll() {
		userService.deleteAll();
		return "Deleted all records";
	}

}
//[1] : https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-mongodb-crud-operations-example/