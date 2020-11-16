package br.com.register.employee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.register.employee.dto.EmployeeOfDepartmentDTO;

@Entity
@Table(name = "funcionario_departamento")
public class EmployeeOfDepartment implements Serializable{
	

	private static final long serialVersionUID = 7568103060674339805L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "funcionario_departamento_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "funcionario_id")
	private Employee employee;
	
	@Column(name = "departamento_id")
	private Department department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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
		EmployeeOfDepartment other = (EmployeeOfDepartment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public EmployeeOfDepartmentDTO toDTO() {
		EmployeeOfDepartmentDTO dto = new EmployeeOfDepartmentDTO();
		dto.setDepartmentDTO(this.department.toDTO());
		dto.setEmployeeDTO(this.employee.toDTO());
		
		return dto;
		
	}
	

}
