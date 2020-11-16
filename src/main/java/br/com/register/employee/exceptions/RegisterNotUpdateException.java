package br.com.register.employee.exceptions;

public class RegisterNotUpdateException extends RuntimeException{

	private static final String MSG = "Registro não pode ser alterado";
	private static final long serialVersionUID = -2659796346835089588L;

	public RegisterNotUpdateException() {
		super(MSG);
	}
	
}
