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
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Otp;
import com.example.demo.model.Session;
import com.example.demo.model.User;
import com.example.demo.service.OtpService;
import com.example.demo.service.SessionService;

@RestController

public class OtpController {

	@Autowired
	private OtpService otpService;

	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/createOtp", method = RequestMethod.POST)
	public String create(@RequestBody(required = false) Otp otp) {
		Otp o = otpService.create(otp);
		return o.toString();
	}

	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/getOtpByUserId", method = RequestMethod.GET)
	public List<Otp> findByUserId(@RequestParam(required = true) String UserId) {
		return otpService.findByUserId(UserId);

	}
	
	@RequestMapping(value = "/sendOTPData", method = RequestMethod.GET)
	public String getOTPData(@RequestParam String phoneNo, String otp) {
		final String uri = "http://nimbusit.co.in/api/swsendUni2.asp?username=t1Foodali&password=26537993&sender=666666&sendto=91"+phoneNo+"&message=Your OTP for FoodAli is "+otp+ ". This OTP is valid for 1 minute only. To generate a new OTP, please go to https://phorons.com/afterlogin";
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
	
	@CrossOrigin(origins = {"*"})
	@RequestMapping(value = "/deleteOtpByUserId", method = RequestMethod.DELETE)
	public String delete(@RequestParam String userid) {
		otpService.delete(userid);
		return "Deleted "+userid;
	}
}