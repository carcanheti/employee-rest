package br.com.register.employee.exceptions;

public class  ServiceConnectionTimeOutException extends RuntimeException{

	private static final long serialVersionUID = 915193678210892845L;

	public ServiceConnectionTimeOutException(String msg) {
		super(msg);
	}
}
