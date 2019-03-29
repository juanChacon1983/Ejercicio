package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorInfo {

	@JsonProperty("timestamp")
	private Date fecha;
	@JsonProperty("status")
	private int statusCode;
	@JsonProperty("error")
	private String error;
	@JsonProperty("message")
	private String message;
	@JsonProperty("path")
	private String uriRequested;

	public ErrorInfo(int statusCode, String error, String message, String uriRequested) {
		this.message = message;
		this.statusCode = statusCode;
		this.uriRequested = uriRequested;
		this.error = error;
	   this.fecha = new Date();
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getUriRequested() {
		return uriRequested;
	}

}