package br.com.register.employee.services;

import java.util.Optional;

import br.com.register.employee.dto.BusinessDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.NewBusinessDTO;
import br.com.register.employee.entities.Business;


public interface BusinessServices {

	DataListOutputDTO<BusinessDTO> findAllBusiness();
	
	Optional<Business> createBusiness(NewBusinessDTO newBusinessDTO);

	BusinessDTO findBusinessById(Long id);

	void updateBusinessById(BusinessDTO businessDTO);

	void deleteBusinessById(BusinessDTO businessDTO);

}
