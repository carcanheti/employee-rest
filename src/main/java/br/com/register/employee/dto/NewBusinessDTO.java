package br.com.register.employee.dto;

import br.com.register.employee.entities.Business;
import io.swagger.annotations.ApiModelProperty;

public class NewBusinessDTO {

	@ApiModelProperty(example = "Analista de Sistemas")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Business toEntity() {
		
		Business entity = new Business();
		entity.setName(this.name);
		
		return entity;
	}
}
