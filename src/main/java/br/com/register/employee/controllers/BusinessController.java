package br.com.register.employee.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.register.employee.dto.BusinessDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.DataOutputDTO;
import br.com.register.employee.dto.NewBusinessDTO;
import br.com.register.employee.entities.Business;
import br.com.register.employee.models.ErrorResponse;
import br.com.register.employee.services.impl.BusinessServicesImpl;
import io.swagger.annotations.*;

@Api(tags = "Cargo")
@RestController
@RequestMapping(value = "/business/parameters")
public class BusinessController {

	private BusinessServicesImpl businessServicesImpl;

	public BusinessController(BusinessServicesImpl businessServicesImpl) {
		this.businessServicesImpl = businessServicesImpl;
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar cargos")
	@GetMapping
	public ResponseEntity<DataListOutputDTO<BusinessDTO>> findAll() {

		DataListOutputDTO<BusinessDTO> list = this.businessServicesImpl.findAllBusiness();

		return ResponseEntity.ok(list);
	}

	@ApiResponses({ @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Salvar cargos")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataOutputDTO<BusinessDTO>> saveBusiness(@RequestBody NewBusinessDTO newBusinessDTO) {

		DataOutputDTO<BusinessDTO> data = new DataOutputDTO<>();
		Optional<Business> op = this.businessServicesImpl.createBusiness(newBusinessDTO);

		if (op.isPresent()) {

			BusinessDTO dto = op.get().toDTO();
			data.setData(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(data);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(data);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Alterar cadastro de cargo")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBusiness(@RequestBody BusinessDTO businessDTO) {
		this.businessServicesImpl.updateBusinessById(businessDTO);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Deletar cargo")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBusiness(@RequestBody BusinessDTO businessDTO) {
		this.businessServicesImpl.deleteBusinessById(businessDTO);
	}

}
