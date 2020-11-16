package br.com.register.employee.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.register.employee.dto.BusinessDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.NewBusinessDTO;
import br.com.register.employee.entities.Business;
import br.com.register.employee.exceptions.NotSavedRegisterException;
import br.com.register.employee.exceptions.RegisterNotDeleteException;
import br.com.register.employee.exceptions.RegisterNotFoundException;
import br.com.register.employee.exceptions.RegisterNotUpdateException;
import br.com.register.employee.repository.BusinessRepository;
import br.com.register.employee.services.BusinessServices;

@Service
public class BusinessServicesImpl implements BusinessServices {
	
	private static final Logger LOGGER = Logger.getLogger(BusinessServicesImpl.class);

	private BusinessRepository businessRepository;

	public BusinessServicesImpl(BusinessRepository businessRepository) {
		this.businessRepository = businessRepository;
	}

	@Override
	public DataListOutputDTO<BusinessDTO> findAllBusiness() {
		DataListOutputDTO<BusinessDTO> data = new DataListOutputDTO<>();

		List<Business> list = this.businessRepository.findAll();
		List<BusinessDTO> listDto = list.stream().map(Business::toDTO).collect(Collectors.toList());
		data.setData(listDto);

		return data;
	}

	@Override
	@Transactional
	public Optional<Business> createBusiness(NewBusinessDTO newBusinessDTO) {

		try {

			Business business = newBusinessDTO.toEntity();

			Business entity = this.businessRepository.save(business);

			return Optional.of(entity);

		} catch (Exception e) {
			LOGGER.error(e);
			throw new NotSavedRegisterException();
		}
	}

	@Override
	public BusinessDTO findBusinessById(Long id) {
		Optional<Business> optional = this.businessRepository.findById(id);
		return optional.orElseThrow(() -> new RegisterNotFoundException()).toDTO();
	}

	@Override
	public void updateBusinessById(BusinessDTO businessDTO) {
		try {
			
			Optional<Business> optional = this.businessRepository.findById(businessDTO.getId());
			var orig = optional.orElseThrow(() -> new RegisterNotFoundException());
			Business dest = businessDTO.toEntity();
			BeanUtils.copyProperties(orig, dest);
			
			this.businessRepository.save(orig);
			
		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotUpdateException();
		}	

	}

	@Override
	public void deleteBusinessById(BusinessDTO businessDTO) {
		try {
			
			Optional<Business> optional = this.businessRepository.findById(businessDTO.getId());
			var obj = optional.orElseThrow(() -> new RegisterNotFoundException());
			this.businessRepository.delete(obj);
			
			
		} catch (Exception e) {
			LOGGER.error(e);
			throw new RegisterNotDeleteException();
		}
		
	}

}
