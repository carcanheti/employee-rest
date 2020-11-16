package br.com.register.employee.entities;

import javax.persistence.Table;

import br.com.register.employee.dto.BossOfDepartmentDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "supervisor_departamento")
public class BossDepartment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "supervisor_departamento_seq", allocationSize = 1)
	private Long id;
	
	
	@OneToOne
	private Department departament;
	
	
	@OneToOne
	private Employee employee;
	
	private boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartament() {
		return departament;
	}

	public void setDepartament(Department departament) {
		this.departament = departament;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public BossOfDepartmentDTO toDTO() {
		BossOfDepartmentDTO dto = new BossOfDepartmentDTO();
		dto.setActive(this.isActive);
		dto.setDepartmentDTO(this.departament.toDTO());
		dto.setEmployeeDTO(this.employee.toDTO());
		
		return dto;
	}
	
	

}
