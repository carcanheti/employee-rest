package br.com.register.employee.dto;


import br.com.register.employee.entities.BossDepartment;
import io.swagger.annotations.ApiModelProperty;

public class BossOfDepartmentDTO {

	
	private EmployeeDTO employeeDTO;


	private DepartmentDTO departmentDTO;

	@ApiModelProperty(value = "true")
	private boolean isActive; 
	
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public BossDepartment toEntity() {
		BossDepartment entity = new BossDepartment();
		entity.setDepartament(this.departmentDTO.toEntity());
		entity.setEmployee(this.employeeDTO.toEntity());
		entity.setActive(this.isActive);

		return entity;
	}

}
