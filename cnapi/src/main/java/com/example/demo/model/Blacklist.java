package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Blacklist {
	@Id
	String id;
	String fromId;
	String toId;

	public Blacklist(String fromId, String toId) {
		super();
		this.fromId = fromId;
		this.toId = toId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	@Override
	public String toString() {
		return "Blacklist [id=" + id + ", fromId=" + fromId + ", toId=" + toId + "]";
	}

}