package com.johnfnash.study.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.johnfnash.study.model.Result;

/**
 * Rest 异常处理器，不需要返回视图。该类会处理所有在执行标有@RequestMapping注解的方法时发生的异常
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LogManager.getLogger(RestExceptionHandler.class.getName());  
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error("Internal error", ex);
		//return super.handleExceptionInternal(ex, body, headers, status, request);
		return new ResponseEntity<Object>(Result.getFailResult("Error,please contact administrator!"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Method not allowed", ex);
		//return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
		return new ResponseEntity<Object>(Result.getFailResult("Request method not allowed!"), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> handleHttpBusinessException(BusinessException ex) {
		log.error("Business Exception", ex);
		return new ResponseEntity<Object>(Result.getFailResult(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ParameterException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> handleHttpParameterException(ParameterException ex) {
		log.error("Parameter Exception", ex);
		return new ResponseEntity<Object>(Result.getFailResult(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> handleException(Exception ex) {
		log.error("Exception", ex);
		return new ResponseEntity<Object>(Result.getFailResult("Exception!"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
