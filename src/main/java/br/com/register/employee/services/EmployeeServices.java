package br.com.register.employee.services;

import java.util.Optional;

import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.DepartmentDTO;
import br.com.register.employee.dto.EmployeeDTO;
import br.com.register.employee.dto.EmployeeOfDepartmentDTO;
import br.com.register.employee.dto.NewEmployeeDTO;
import br.com.register.employee.entities.Employee;
import br.com.register.employee.entities.EmployeeOfDepartment;


public interface EmployeeServices {

	DataListOutputDTO<EmployeeDTO> findAllEmployees();

	Optional<Employee> createEmployee(NewEmployeeDTO newEmployeeDTO);

	EmployeeDTO findEmployeeById(Long id);
	
	DataListOutputDTO<EmployeeDTO> findEmployeesByDepartment(DepartmentDTO departmentDTO);

	void updateEmployeeById(EmployeeDTO employeeDTO);

	void deleteEmployeeById(EmployeeDTO employeeDTO);
	
	void updateDepartmentByEmployee(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO);
	
	Optional<EmployeeOfDepartment> saveEmployeeByDepartment(EmployeeOfDepartmentDTO employeeOfDepartmentDTO);

}
