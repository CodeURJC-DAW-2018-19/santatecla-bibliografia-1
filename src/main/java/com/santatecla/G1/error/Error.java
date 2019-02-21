package com.santatecla.G1.error;

public class Error {
	private int status;
	Exception exception;
	
	public Error(int status, Exception exception) {
		super();
		this.status = status;
		this.exception = exception;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
}
