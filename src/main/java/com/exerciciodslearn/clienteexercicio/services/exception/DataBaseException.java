package com.exerciciodslearn.clienteexercicio.services.exception;

public class DataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    
	public DataBaseException(String message) {
		super(message);
	}
}
