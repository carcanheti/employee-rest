package br.com.register.employee.dto;


import br.com.register.employee.entities.Business;
import io.swagger.annotations.ApiModelProperty;

public class BusinessDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	
	@ApiModelProperty(example = "Analista de Sistemas")
	private String name;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public Business toEntity() {
		Business entity = new Business();
		entity.setName(this.name);
		entity.setId(this.id);
		
		return entity;
	}
	
}
