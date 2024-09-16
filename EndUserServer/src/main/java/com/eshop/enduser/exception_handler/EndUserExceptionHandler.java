package com.eshop.enduser.exception_handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class EndUserExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleArgumentValidationExceptions( MethodArgumentNotValidException ex) {
	    
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HandlerMethodValidationException.class)
	public Map<String, Object> handleParameterValidationExceptions( HandlerMethodValidationException ex) {
	    
		Map<String, Object> errors = new HashMap<>();
		ex.getAllValidationResults().forEach((error) ->{
			String param = ((ParameterValidationResult) error).getMethodParameter().getParameterName();
			List<String> errorMessage = ((ParameterValidationResult) error).getResolvableErrors().stream().map(e -> e.getDefaultMessage()).toList();
			errors.put(param, errorMessage);
		});
	    
		return errors;
	}
}
