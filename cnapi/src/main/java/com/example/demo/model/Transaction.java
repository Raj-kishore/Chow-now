package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Transaction {
	@Id
	String id;
	
	private String userId;
	private String cookId;
	private String dishId;
	private LocalDateTime requestTime;
	
	private boolean bstatus;

	
	
	public Transaction(String userId, String cookId, String dishId) {
		super();
		this.userId = userId;
		this.cookId = cookId;
		this.dishId = dishId;
		this.requestTime = LocalDateTime.now();
		this.bstatus = false;
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
	public String getCookId() {
		return cookId;
	}
	public void setCookId(String cookId) {
		this.cookId = cookId;
	}
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	

	
	

	public LocalDateTime getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}
	public boolean isBstatus() {
		return bstatus;
	}
	public void setBstatus(boolean bstatus) {
		this.bstatus = bstatus;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", userId=" + userId + ", cookId=" + cookId + ", dishId=" + dishId
				+ ", requestTime=" + requestTime + ", bstatus=" + bstatus + "]";
	}
	
	
	
}
