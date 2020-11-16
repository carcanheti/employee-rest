package br.com.register.employee.dto;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.register.employee.entities.Employee;
import io.swagger.annotations.ApiModelProperty;

public class NewEmployeeDTO {
	
	@ApiModelProperty(example = "Maria da Silva")
	private String name;
	
	@ApiModelProperty(example = "32")
	private Integer age;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(example = "02/02/1970")
	private LocalDate birthday;
	
	@ApiModelProperty(example = "26.789.214-5")
	private String document;
	
	private NewBusinessDTO newBusinessDTO;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
	
	public NewBusinessDTO getNewBusinessDTO() {
		return newBusinessDTO;
	}
	
	public void setNewBusinessDTO(NewBusinessDTO newBusinessDTO) {
		this.newBusinessDTO = newBusinessDTO;
	}
	
	

	public Employee toEntity() {
		Employee entity = new Employee();
		
		entity.setAge(this.age);
		entity.setBirthday(this.birthday);
		entity.setDocument(this.document);
		entity.setName(this.name);
		entity.setBusinessId(this.newBusinessDTO.toEntity());
		
		
		return entity;
	}
	
}
