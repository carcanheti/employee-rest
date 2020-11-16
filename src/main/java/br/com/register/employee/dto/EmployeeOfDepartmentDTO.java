package br.com.register.employee.dto;

import br.com.register.employee.entities.EmployeeOfDepartment;

public class EmployeeOfDepartmentDTO {

	private EmployeeDTO employeeDTO;

	private DepartmentDTO departmentDTO;
	
	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}

	public DepartmentDTO getDepartmentDTO() {
		return departmentDTO;
	}

	public void setDepartmentDTO(DepartmentDTO departmentDTO) {
		this.departmentDTO = departmentDTO;
	}

	public EmployeeOfDepartment toEntity() {
		
		EmployeeOfDepartment entity = new EmployeeOfDepartment();
		entity.setDepartment(this.departmentDTO.toEntity());
		entity.setEmployee(this.employeeDTO.toEntity());
		
		return entity;
	}
	
}
