package com.example.amazonSnsG.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
 
	private final String id;
	private final String message;

	@JsonCreator
	public Notification(@JsonProperty("id") final String id, @JsonProperty("message") final String message) {
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return id;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + "]";
	}
}

