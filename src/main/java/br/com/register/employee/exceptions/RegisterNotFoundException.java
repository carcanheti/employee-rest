package br.com.register.employee.exceptions;

public class RegisterNotFoundException extends RuntimeException{

	private static final String MSG = "Registro não encontrado";
	private static final long serialVersionUID = -2659796346835089588L;

	public RegisterNotFoundException() {
		super(MSG);
	}
	
}
