package com.exercise.bean;

import java.io.Serializable;

public class ResponseBaseBean implements Serializable {

	private static final long serialVersionUID = -1439739911241638678L;
	private String message;

	public ResponseBaseBean(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
