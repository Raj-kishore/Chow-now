package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Session {
	@Id
	private String id;
	private String userId;
	private String accessToken;
	private String refreshToken;
	
	@Indexed(expireAfterSeconds = 12*3600)
	private LocalDateTime registeredTimeStamp;

	public Session(String userId, String accessToken, String refreshToken) {
		super();
		this.userId = userId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.registeredTimeStamp =  LocalDateTime.now();
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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public LocalDateTime getRegisteredTimeStamp() {
		return registeredTimeStamp;
	}

	public void setRegisteredTimeStamp(LocalDateTime registeredTimeStamp) {
		this.registeredTimeStamp = registeredTimeStamp;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", userId=" + userId + ", accessToken=" + accessToken + ", refreshToken="
				+ refreshToken + ", registeredTime=" + registeredTimeStamp + "]";
	}

	

}