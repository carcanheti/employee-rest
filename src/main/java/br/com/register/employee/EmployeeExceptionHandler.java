package br.com.register.employee;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.register.employee.models.ErrorDetails;
import br.com.register.employee.models.ErrorResponse;
import br.com.register.employee.exceptions.ServiceConnectionTimeOutException;

import java.util.List;
import java.util.UUID;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERRO_SISTEMA = "Ocorreu um erro interno no sistema, tente novamente e se o problema persistir entre em contato com o "
			+ "administrador do sistema";

	private static final String MSG_SISTEMA_INDISPONIVEL = "Micro serviço de cadastro temporariamente indisponível";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ServiceConnectionTimeOutException.class)
	public ResponseEntity<Object> handleServiceConnectionTimeOutException(ServiceConnectionTimeOutException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

		ErrorResponse error = createErrorBuilder(status, MSG_SISTEMA_INDISPONIVEL, null);

		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		ErrorResponse error = createErrorBuilder(status, MSG_ERRO_SISTEMA, null);

		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}

	
	private ErrorResponse createErrorBuilder(HttpStatus status, String message, List<String> fields) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setUniqueId(UUID.randomUUID().toString());
		errorDetails.setInformationCode("error.business.request.invalid");
		errorDetails.setMessage(message);
		errorDetails.setArgs(fields);

		ErrorResponse error = new ErrorResponse(errorDetails);
		error.setTimestamp(LocalDateTime.now().toString());
		error.setStatus(status.value());
		error.setMessage(message);
		error.setPath("path");
		return error;
	}
}
