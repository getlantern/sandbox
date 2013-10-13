package org.lantern.sandbox.java8repatterning.example;

public class MyEventData {
	private String message;
	private long timestamp;

	public MyEventData(String message, long timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}
}
