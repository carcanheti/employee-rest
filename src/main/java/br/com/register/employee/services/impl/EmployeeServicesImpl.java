package br.com.register.employee.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.register.employee.dto.EmployeeDTO;
import br.com.register.employee.dto.EmployeeOfDepartmentDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.DepartmentDTO;
import br.com.register.employee.dto.NewEmployeeDTO;
import br.com.register.employee.entities.Business;
import br.com.register.employee.entities.Department;
import br.com.register.employee.entities.Employee;
import br.com.register.employee.entities.EmployeeOfDepartment;
import br.com.register.employee.exceptions.NotSavedRegisterException;
import br.com.register.employee.exceptions.RegisterNotDeleteException;
import br.com.register.employee.exceptions.RegisterNotFoundException;
import br.com.register.employee.exceptions.RegisterNotUpdateException;
import br.com.register.employee.repository.EmployeeRepository;
import br.com.register.employee.services.EmployeeServices;

@Service
public class EmployeeServicesImpl implements EmployeeServices {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeServicesImpl.class);

	private EmployeeRepository employeeRepository;

	private DepartmentServicesImpl departmentServicesImpl;
	
	private BusinessServicesImpl businessServicesImpl;

	private HistoryEmployeeByDepartmentServicesImpl historyEmployeeByDepartmentServicesImpl;
	
	private EmployeeOfDepartmentServiceImpl employeeOfDepartmentServiceImpl;

	public EmployeeServicesImpl(EmployeeRepository employeeRepository, DepartmentServicesImpl departmentServicesImpl,
			HistoryEmployeeByDepartmentServicesImpl historyEmployeeByDepartmentServicesImpl,
			BusinessServicesImpl businessServicesImpl, 
			EmployeeOfDepartmentServiceImpl employeeOfDepartmentServiceImpl) {
		this.employeeRepository = employeeRepository;
		this.departmentServicesImpl = departmentServicesImpl;
		this.historyEmployeeByDepartmentServicesImpl = historyEmployeeByDepartmentServicesImpl;
		this.businessServicesImpl = businessServicesImpl;
		this.employeeOfDepartmentServiceImpl= employeeOfDepartmentServiceImpl;
	}

	@Override
	public DataListOutputDTO<EmployeeDTO> findAllEmployees() {
		DataListOutputDTO<EmployeeDTO> data = new DataListOutputDTO<>();

		List<Employee> list = this.employeeRepository.findAll();
		List<EmployeeDTO> listDto = list.stream().map(Employee::toDTO).collect(Collectors.toList());
		data.setData(listDto);

		return data;
	}

	@Override
	public DataListOutputDTO<EmployeeDTO> findEmployeesByDepartment(DepartmentDTO departmentDTO) {

		DataListOutputDTO<EmployeeDTO> data = new DataListOutputDTO<>();

		DepartmentDTO depDTO = this.departmentServicesImpl.findDepartmentId(departmentDTO.getId());

		List<Employee> list = this.employeeRepository.findEmployeesByDepartment(depDTO.getId());
		List<EmployeeDTO> listDto = list.stream().map(Employee::toDTO).collect(Collectors.toList());
		data.setData(listDto);

		return data;
	}

	@Override
	public Optional<Employee> createEmployee(NewEmployeeDTO newEmployeeDTO) {
		try {

			Employee employee = newEmployeeDTO.toEntity();
			
			Optional<Business> business = this.businessServicesImpl.createBusiness(newEmployeeDTO.getNewBusinessDTO());
			if (business.isPresent()) {
				employee.setBusinessId(business.get());
				
			}

			Employee entity = this.employeeRepository.save(employee);

			return Optional.of(entity);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new NotSavedRegisterException();
		}
	}

	@Override
	public EmployeeDTO findEmployeeById(Long id) {
		Optional<Employee> optional = this.employeeRepository.findById(id);
		return optional.orElseThrow(() -> new RegisterNotFoundException()).toDTO();
	}

	@Override
	public void updateEmployeeById(EmployeeDTO employeeDTO) {
		try {

			Optional<Employee> optional = this.employeeRepository.findById(employeeDTO.getId());
			var orig = optional.orElseThrow(() -> new RegisterNotFoundException());
			Employee dest = employeeDTO.toEntity();
			BeanUtils.copyProperties(orig, dest);

			this.employeeRepository.save(orig);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotUpdateException();
		}

	}

	@Override
	public void deleteEmployeeById(EmployeeDTO employeeDTO) {
		try {

			Optional<Employee> optional = this.employeeRepository.findById(employeeDTO.getId());
			var obj = optional.orElseThrow(() -> new RegisterNotFoundException());
			this.employeeRepository.delete(obj);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotDeleteException();
		}

	}

	@Override
	public void updateDepartmentByEmployee(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO) {

		try {

			Optional<Employee> opEmp = this.employeeRepository.findById(employeeDTO.getId());
			Department departmentEntity = this.departmentServicesImpl.findDepartmentId(departmentDTO.getId())
					.toEntity();

			if (opEmp.isPresent()) {

				this.historyEmployeeByDepartmentServicesImpl.historyDepartmentByEmployee(opEmp.get(), departmentEntity);
			}

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotFoundException();
		}

	}
	
	
	@Override
	@Transactional
	public Optional<EmployeeOfDepartment> saveEmployeeByDepartment(EmployeeOfDepartmentDTO employeeOfDepartmentDTO) {

		try {

			Optional<EmployeeOfDepartment> op = this.employeeOfDepartmentServiceImpl.createEmployeeOfDepartament(employeeOfDepartmentDTO);
			
			if( op.isPresent()) {
				return Optional.of(op.get());
			}
			return Optional.empty();

		} catch (RegisterNotFoundException e) {
			LOGGER.error(e);
			throw new RegisterNotFoundException();
		}

	}
	

}
