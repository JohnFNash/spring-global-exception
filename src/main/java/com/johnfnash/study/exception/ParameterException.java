package com.johnfnash.study.exception;

public class ParameterException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1549070845387246846L;

	public ParameterException(String message) {    
        super(message);    
    }    
    
    public ParameterException(Throwable cause) {    
        super(cause);    
    }    
    
    public ParameterException(String message, Throwable cause) {    
        super(message, cause);    
    }
	
}
