package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Otp {
	@Id
	private String id;
	private String userId;
	private String otp;

	@Indexed(expireAfterSeconds = 300) //5 minute
	private LocalDateTime registeredTime;

	public Otp(String userId, String otp) {
		super();
		this.userId = userId;
		this.otp = otp;

		// LocalDateTime datetime1 = LocalDateTime.now();
//		    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
//		    String formatDateTime = datetime1.format(format);   

		this.registeredTime = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getRegisteredDate() {
		return registeredTime;
	}

	public void setRegisteredDate(LocalDateTime registeredTime) {
		this.registeredTime = registeredTime;
	}

	@Override
	public String toString() {
		return "Otp [id=" + id + ", userId=" + userId + ", otp=" + otp + ", registeredTime=" + registeredTime + "]";
	}

}
