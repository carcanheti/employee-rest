package br.com.register.employee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.register.employee.dto.DepartmentDTO;

@Entity
@Table(name = "departamento")
public class Department implements Serializable {


	private static final long serialVersionUID = -382911127534127758L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "departamento_seq", allocationSize = 1)
	private Long id;
	
	
	@Column(name = "name", length = 50)
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
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public DepartmentDTO toDTO() {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setName(this.name);
		dto.setId(this.id);
		
		return dto;
	}
	
	
}
