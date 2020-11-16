package br.com.register.employee.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import br.com.register.employee.dto.BossOfDepartmentDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.DepartmentDTO;
import br.com.register.employee.dto.EmployeeDTO;
import br.com.register.employee.dto.NewDepartmentDTO;
import br.com.register.employee.entities.BossDepartment;
import br.com.register.employee.entities.Department;
import br.com.register.employee.exceptions.NotSavedRegisterException;
import br.com.register.employee.exceptions.RegisterNotDeleteException;
import br.com.register.employee.exceptions.RegisterNotFoundException;
import br.com.register.employee.exceptions.RegisterNotUpdateException;
import br.com.register.employee.repository.DepartamentRepository;
import br.com.register.employee.services.DepartmentServices;



@Service
public class DepartmentServicesImpl implements DepartmentServices {
	
	
	private static final Logger LOGGER = Logger.getLogger(DepartmentServicesImpl.class);
	
	private DepartamentRepository departamentRepository;

	public DepartmentServicesImpl(DepartamentRepository departamentRepository) {
		this.departamentRepository = departamentRepository;
	}

	@Override
	public DataListOutputDTO<DepartmentDTO> findAllDepartment() {
		DataListOutputDTO<DepartmentDTO> data = new DataListOutputDTO<>();

		List<Department> list = this.departamentRepository.findAll();
		List<DepartmentDTO> listDto = list.stream().map(Department::toDTO).collect(Collectors.toList());
		data.setData(listDto);

		return data;
	}
	
	@Override
	public DataListOutputDTO<BossOfDepartmentDTO> findBossByDepartment(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO) {
		DataListOutputDTO<BossOfDepartmentDTO> data = new DataListOutputDTO<>();

		List<BossDepartment> list = this.departamentRepository.findBossByDepartment(employeeDTO.getId());
		List<BossOfDepartmentDTO> listDto = list.stream().map(BossDepartment::toDTO).collect(Collectors.toList());
		data.setData(listDto);

		return data;
	}

	@Override
	public Optional<Department> createDepartment(NewDepartmentDTO newDepartmentDTO) {
		try {

			Department department = newDepartmentDTO.toEntity();

			Department entity = this.departamentRepository.save(department);

			return Optional.of(entity);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new NotSavedRegisterException();
		}
		
	}

	@Override
	public DepartmentDTO findDepartmentId(Long id) {
		Optional<Department> optional = this.departamentRepository.findById(id);
		return optional.orElseThrow(() -> new RegisterNotFoundException()).toDTO();
	}

	@Override
	public void updateDepartmentId(DepartmentDTO departmentDTO) {
		try {

			Optional<Department> optional = this.departamentRepository.findById(departmentDTO.getId());
			var orig = optional.orElseThrow(() -> new RegisterNotFoundException());
			Department dest = departmentDTO.toEntity();
			BeanUtils.copyProperties(orig, dest);

			this.departamentRepository.save(orig);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotUpdateException();
		}

	}

	@Override
	public void deleteDepartmentId(DepartmentDTO departmentDTO) {
		try {

			Optional<Department> optional = this.departamentRepository.findById(departmentDTO.getId());
			var obj = optional.orElseThrow(() -> new RegisterNotFoundException());
			this.departamentRepository.delete(obj);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotDeleteException();
		}

	}

}
