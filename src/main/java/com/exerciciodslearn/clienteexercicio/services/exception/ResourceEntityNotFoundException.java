package com.exerciciodslearn.clienteexercicio.services.exception;

public class ResourceEntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
    
	public ResourceEntityNotFoundException(String message){
		super(message);
	}
}
