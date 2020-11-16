package br.com.register.employee.services;

import br.com.register.employee.entities.Department;
import br.com.register.employee.entities.Employee;

public interface HistoryEmployeeByDepartmentServices {

	void historyDepartmentByEmployee(Employee employee, Department department);
	
}
