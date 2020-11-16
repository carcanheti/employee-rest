package br.com.register.employee.services;

import java.util.Optional;

import br.com.register.employee.dto.BossOfDepartmentDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.DepartmentDTO;
import br.com.register.employee.dto.EmployeeDTO;
import br.com.register.employee.dto.NewDepartmentDTO;
import br.com.register.employee.entities.Department;


public interface DepartmentServices {

	DataListOutputDTO<DepartmentDTO> findAllDepartment();

	Optional<Department> createDepartment(NewDepartmentDTO newDepartmentDTO);

	DepartmentDTO findDepartmentId(Long id);
	
	DataListOutputDTO<BossOfDepartmentDTO> findBossByDepartment(EmployeeDTO employeeDTO,DepartmentDTO departmentDTO );

	void updateDepartmentId(DepartmentDTO departmentDTO);

	void deleteDepartmentId(DepartmentDTO departmentDTO);
}
