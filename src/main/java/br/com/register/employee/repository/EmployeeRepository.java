package br.com.register.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.register.employee.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
	@Query(value = " select new br.com.register.employee.dto.EmployeeDTO(  "
			+ " f.name "
			+ " f.age "
			+ " f.document "
			+ " ) "
			+ " from funcionario_departamento fd"
			+ " inner join funcionario f ON f.id = funcionario_id "
			+ " inner join departamento dp ON dp.id = departamento_id "
			+ " where fd.departamento_id =:idDepartament )"
			, nativeQuery = true )
	public List<Employee> findEmployeesByDepartment(@Param("idDepartament") Long idDepartament );
	
}
