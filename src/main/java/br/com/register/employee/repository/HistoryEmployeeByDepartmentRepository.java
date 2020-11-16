package br.com.register.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.register.employee.entities.HistoryEmployeeByDepartment;

@Repository
public interface HistoryEmployeeByDepartmentRepository extends JpaRepository<HistoryEmployeeByDepartment, Long>{

}
