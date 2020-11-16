package br.com.register.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.register.employee.entities.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>{

	
	@Query(value = " select b from Business b where b.name =:name ")
	public Optional<Business> findBusinessByName(@Param("name") String name);
}
