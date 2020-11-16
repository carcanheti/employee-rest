package br.com.register.employee.dto;


import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.register.employee.entities.Employee;
import io.swagger.annotations.ApiModelProperty;

public class EmployeeDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	
	@ApiModelProperty(example = "Maria da Silva")
	private String name;
	
	@ApiModelProperty(example = "32")
	private Integer age;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(example = "02/02/1970")
	private LocalDate birthday;
	
	@ApiModelProperty(example = "26.789.214-5")
	private String document;
	
	
	private BusinessDTO businessDTO;

	
	public EmployeeDTO() {
		
	}
	
	public EmployeeDTO(String name, Integer age, String document) {
		this.name = name;
		this.age = age;
		this.document = document;
	}
	
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

	public BusinessDTO getBusinessDTO() {
		return businessDTO;
	}
	
	public void setBusinessDTO(BusinessDTO businessDTO) {
		this.businessDTO = businessDTO;
	}

	public Employee toEntity() {
		Employee entity = new Employee();
		
		entity.setAge(this.age);
		entity.setBirthday(this.birthday);
		entity.setDocument(this.document);
		entity.setName(this.name);
		entity.setBusinessId(this.businessDTO.toEntity());
		entity.setId(this.id);
		
		return entity;
	}
	
}
