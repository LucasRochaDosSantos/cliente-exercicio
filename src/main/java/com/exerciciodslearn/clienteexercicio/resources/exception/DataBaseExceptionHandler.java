package com.exerciciodslearn.clienteexercicio.resources.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exerciciodslearn.clienteexercicio.services.exception.DataBaseException;

@ControllerAdvice
public class DataBaseExceptionHandler {
   
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> EntityNotFound(DataBaseException errorEntity, HttpServletRequest request){
		StandardError error = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		error.setTimeStamp(Instant.now());
		error.setMessage(errorEntity.getMessage());
		error.setStatus(status.value());
		error.setError("DataBaseException ");
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(error);
		
	}
}
