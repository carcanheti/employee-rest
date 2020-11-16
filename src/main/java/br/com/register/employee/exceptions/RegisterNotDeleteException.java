package br.com.register.employee.exceptions;

public class RegisterNotDeleteException extends RuntimeException{

	private static final String MSG = "Não foi possível apagar o registro";
	private static final long serialVersionUID = 7052852653434972223L;

	public RegisterNotDeleteException() {
		super(MSG);
	}
	
}
