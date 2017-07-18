package com.noob.stream.model;

import lombok.Data;

@Data
public class Result {

	private int resultCode;
	private String errorCode;
	private String errorDesc;
	private String message;
	private String tracer;

}
