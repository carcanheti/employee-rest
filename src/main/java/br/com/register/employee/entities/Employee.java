package br.com.register.employee.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.register.employee.dto.EmployeeDTO;


@Entity
@Table(name = "funcionario")
public class Employee implements Serializable {

	
	private static final long serialVersionUID = -3427741816313593617L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "funcionario_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	
	@Column(name = "age")
	private Integer age;
	
	
	
	@Column(name = "birthday", columnDefinition = "DATE")
	private LocalDate birthday;
	
	
	@Column( name = "document", length = 50)
	private String document;
	
	@ManyToOne()
	@JoinColumn(name = "cargo_id")
	private Business businessId;
	
	
	@ManyToMany
	@JoinTable(
			  name = "funcionario_departamento", 
			  joinColumns = @JoinColumn(name = "funcionario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "departamento_id"))
	private List<Department> listDepartment;


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


	public Business getBusinessId() {
		return businessId;
	}


	public void setBusinessId(Business businessId) {
		this.businessId = businessId;
	}
	
	public List<Department> getListDepartment() {
		return listDepartment;
	}
	
	public void setListDepartment(List<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public EmployeeDTO toDTO() {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setAge(this.age);
		dto.setBirthday(this.birthday);
		dto.setDocument(this.document);
		dto.setName(this.name);
		dto.setId(this.id);
		dto.setBusinessDTO(this.businessId.toDTO());
		
		return dto;
	}
	
	
	
}
