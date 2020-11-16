package br.com.register.employee.services.impl;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import br.com.register.employee.dto.EmployeeOfDepartmentDTO;
import br.com.register.employee.entities.Department;
import br.com.register.employee.entities.Employee;
import br.com.register.employee.entities.EmployeeOfDepartment;
import br.com.register.employee.exceptions.RegisterNotFoundException;
import br.com.register.employee.repository.EmployeeOfDepartmentRepository;
import br.com.register.employee.repository.EmployeeRepository;
import br.com.register.employee.services.EmployeeOfDepartmentService;

@Service
public class EmployeeOfDepartmentServiceImpl implements EmployeeOfDepartmentService {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeOfDepartmentServiceImpl.class);

	private EmployeeRepository employeeRepository;

	private DepartmentServicesImpl departmentServicesImpl;
	
	private EmployeeOfDepartmentRepository employeeOfDepartmentRepository;
	
	public EmployeeOfDepartmentServiceImpl(EmployeeRepository employeeRepository, DepartmentServicesImpl departmentServicesImpl, 
			EmployeeOfDepartmentRepository employeeOfDepartmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentServicesImpl = departmentServicesImpl;
		this.employeeOfDepartmentRepository = employeeOfDepartmentRepository;
	}
	
	@Override
	public Optional<EmployeeOfDepartment> createEmployeeOfDepartament(EmployeeOfDepartmentDTO employeeOfDepartmentDTO) {
		
		try {
			
			Optional<Employee> emp = this.employeeRepository.findById(employeeOfDepartmentDTO.getEmployeeDTO().getId());
			
			Department departmentEntity = this.departmentServicesImpl.findDepartmentId(employeeOfDepartmentDTO.getDepartmentDTO().getId())
					.toEntity();

			if (emp.isPresent()  && departmentEntity != null) {
				
				EmployeeOfDepartment entity = new EmployeeOfDepartment();
				entity.setDepartment(departmentEntity);
				entity.setEmployee(emp.get());
				
				EmployeeOfDepartment ep = this.employeeOfDepartmentRepository.save(entity);
				
				return Optional.of(ep);
			}

		} catch (RegisterNotFoundException e) {
			LOGGER.error(e);
			throw new RegisterNotFoundException();
		}
		return Optional.empty();
		
	}

}
