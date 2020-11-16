package br.com.register.employee.services;

import java.util.Optional;

import br.com.register.employee.dto.EmployeeOfDepartmentDTO;
import br.com.register.employee.entities.EmployeeOfDepartment;

public interface EmployeeOfDepartmentService {

	Optional<EmployeeOfDepartment> createEmployeeOfDepartament(EmployeeOfDepartmentDTO employeeOfDepartmentDTO);
	
}
