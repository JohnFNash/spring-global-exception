package com.johnfnash.study.model;

public class Result {
	private boolean success;
	private Object result;
	
	public Result() {
		super();
	}

	public Result(boolean success, Object result) {
		super();
		this.success = success;
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	public static Result getSuccessResult(Object data) {
		return new Result(true, data);
	}

	public static Result getFailResult(Object error) {
		return new Result(false, error);
	}
	
}
