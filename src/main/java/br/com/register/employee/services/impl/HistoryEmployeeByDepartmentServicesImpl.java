package br.com.register.employee.services.impl;

import java.time.LocalDateTime;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.register.employee.entities.Department;
import br.com.register.employee.entities.Employee;
import br.com.register.employee.entities.HistoryEmployeeByDepartment;
import br.com.register.employee.entities.UserLog;
import br.com.register.employee.exceptions.NotSavedRegisterException;
import br.com.register.employee.repository.HistoryEmployeeByDepartmentRepository;
import br.com.register.employee.services.HistoryEmployeeByDepartmentServices;

@Service
public class HistoryEmployeeByDepartmentServicesImpl implements HistoryEmployeeByDepartmentServices {

	private static final Logger LOGGER = Logger.getLogger(HistoryEmployeeByDepartmentServicesImpl.class);
	
	HistoryEmployeeByDepartmentRepository historyEmployeeByDepartmentRepository;
	 
	public HistoryEmployeeByDepartmentServicesImpl(HistoryEmployeeByDepartmentRepository historyEmployeeByDepartmentRepository) {
		this.historyEmployeeByDepartmentRepository = historyEmployeeByDepartmentRepository;
	}
	
	@Override
	@Transactional
	public void historyDepartmentByEmployee(Employee employee, Department department) {
		
		try {
			

			HistoryEmployeeByDepartment history = new HistoryEmployeeByDepartment();
			history.setDepartment(department);
			history.setEmployee(employee);
			
			UserLog log = new UserLog();
			log.setCreateAt(LocalDateTime.now());
			
			// TODO Pegar usuário pelo token passado no request
			log.setCreatedBy("user_default");
			history.setLog(log);
		
			
			this.historyEmployeeByDepartmentRepository.save(history);
			
			
		} catch (Exception e) {
			LOGGER.error(e);
			throw new NotSavedRegisterException();
		}
		
		
	}

}
