package br.com.register.employee.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ErrorResponse")
public class ErrorResponse {

    @ApiModelProperty(example = "O timestamp em que o erro ocorreu.")
    private String timestamp;
    @ApiModelProperty(example = "O status HTTP. Deve ser o mesmo status retornado como resposta da requisição.")
    private Integer status;
    @ApiModelProperty(example = "Bad Request")
    private String errorMsg;
    @ApiModelProperty(example = "Dados obrigatórios não preenchidos.")
    private String message;
    private String method;
    private String path;
    private ErrorDetails errorDetails;

    public ErrorResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return errorMsg;
    }

    public void setError(String error) {
        this.errorMsg = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }
}

