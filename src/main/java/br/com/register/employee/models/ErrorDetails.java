package br.com.register.employee.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("ErrorDetails")
public class ErrorDetails {

    @ApiModelProperty(example = "e9ef6d22-7f46-426b-8121-417e1714ad6d")
    private String uniqueId;
    @ApiModelProperty(example = "error.business.request.invalid")
    private String informationCode;
    @ApiModelProperty(example = "O atributo {1} deve ser preenchido.")
    private String message;
    @ApiModelProperty(example = "List [ \"accountId\" ]")
    private List<String> args;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getInformationCode() {
        return informationCode;
    }

    public void setInformationCode(String informationCode) {
        this.informationCode = informationCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}


