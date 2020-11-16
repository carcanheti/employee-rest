package br.com.register.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.register.employee.entities.EmployeeOfDepartment;

@Repository
public interface EmployeeOfDepartmentRepository extends JpaRepository<EmployeeOfDepartment, Long>{

}
