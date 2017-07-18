package com.noob.stream.model;

public class SelfException extends Exception {
	private static final long serialVersionUID = 988592186626034036L;

	public SelfException() {
		super();
	}

	public SelfException(String arg0) {
		super(arg0);
	}

	public SelfException(Throwable arg1) {
		super(arg1);
	}

	public SelfException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
