package br.com.register.employee.exceptions;

public class NotSavedRegisterException extends RuntimeException{

	private static final String MSG = "Erro inesperado não foi possível salvar o registro";
	private static final long serialVersionUID = 7052852653434972223L;

	public NotSavedRegisterException() {
		super(MSG);
	}
	
	
}