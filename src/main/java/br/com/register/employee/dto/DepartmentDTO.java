package br.com.register.employee.dto;

import br.com.register.employee.entities.Department;
import io.swagger.annotations.ApiModelProperty;

public class DepartmentDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	
	@ApiModelProperty(example = "Financeiro")
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
	
	
	public Department toEntity() {
		Department entity = new Department();
		entity.setName(this.name);
		entity.setId(this.id);
		
		return entity;
	}
	
}
