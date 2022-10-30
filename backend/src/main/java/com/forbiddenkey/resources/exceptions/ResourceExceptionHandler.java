package com.forbiddenkey.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	private static HttpStatus status;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandartError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		StandartError err = new StandartError();
		setStatus(HttpStatus.NOT_FOUND);
		err.setTimestamp(Instant.now());
		err.setStatus(getStatus().value());
		err.setError("Resource Not Found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(getStatus()).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandartError> databaseException(DatabaseException e, HttpServletRequest request){
		StandartError err = new StandartError();
		setStatus(HttpStatus.BAD_REQUEST);
		err.setTimestamp(Instant.now());
		err.setStatus(getStatus().value());
		err.setError("Database Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(getStatus()).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError err = new ValidationError();
		setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		err.setTimestamp(Instant.now());
		err.setStatus(getStatus().value());
		err.setError("Validation Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(getStatus()).body(err);
	}

	public static HttpStatus getStatus() {
		return status;
	}

	public static void setStatus(HttpStatus status) {
		ResourceExceptionHandler.status = status;
	}
	
	
}

