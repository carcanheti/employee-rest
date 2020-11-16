package br.com.register.employee.dto;

import br.com.register.employee.entities.Department;
import io.swagger.annotations.ApiModelProperty;

public class NewDepartmentDTO {

	@ApiModelProperty(example = "Financeiro")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Department toEntity() {
		
		Department entity = new Department();
		entity.setName(this.name);
		
		return entity;
	}
}
