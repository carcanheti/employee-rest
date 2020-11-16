package br.com.register.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.register.employee.entities.BossDepartment;
import br.com.register.employee.entities.Department;

@Repository
public interface DepartamentRepository extends JpaRepository<Department, Long>{

	
	@Query( value = " select b from BossDepartment b "
			+ " where b.employee.id =:id ")
	public List<BossDepartment> findBossByDepartment(@Param("id") Long id);
}
