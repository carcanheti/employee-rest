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

import br.com.register.employee.dto.DepartmentDTO;
import br.com.register.employee.dto.EmployeeDTO;
import br.com.register.employee.dto.BossOfDepartmentDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.DataOutputDTO;
import br.com.register.employee.dto.NewDepartmentDTO;
import br.com.register.employee.entities.Department;
import br.com.register.employee.models.ErrorResponse;
import br.com.register.employee.services.impl.DepartmentServicesImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Departamento")
@RestController
@RequestMapping(value = "/department/parameters")
public class DepartmentController {
	
	private DepartmentServicesImpl departmentServicesImpl;

	public DepartmentController(DepartmentServicesImpl departmentServicesImpl) {
		this.departmentServicesImpl = departmentServicesImpl;
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar departamentos")
	@GetMapping
	public ResponseEntity<DataListOutputDTO<DepartmentDTO>> findAll() {

		DataListOutputDTO<DepartmentDTO> list = this.departmentServicesImpl.findAllDepartment();

		return ResponseEntity.ok(list);
	}
	
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
		@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
		@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar departamentos")
	@GetMapping(value = "/boss-departament")
	public ResponseEntity<DataListOutputDTO<BossOfDepartmentDTO>> findBossByDepartment(
			@RequestBody EmployeeDTO employeeDTO, DepartmentDTO departmentDTO) {
	
		DataListOutputDTO<BossOfDepartmentDTO> list = this.departmentServicesImpl.findBossByDepartment(employeeDTO, departmentDTO);
	
		return ResponseEntity.ok(list);
	}
	
	

	@ApiResponses({ @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Salvar departamento")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataOutputDTO<DepartmentDTO>> saveDepartment(@RequestBody NewDepartmentDTO newDepartmentDTO) {

		DataOutputDTO<DepartmentDTO> data = new DataOutputDTO<>();
		Optional<Department> op = this.departmentServicesImpl.createDepartment(newDepartmentDTO);

		if (op.isPresent()) {

			DepartmentDTO dto = op.get().toDTO();
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
	@ApiOperation("Alterar cadastro de departamentos")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
		this.departmentServicesImpl.updateDepartmentId(departmentDTO);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Deletar departamentos")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDepartment(@RequestBody DepartmentDTO departmentDTO) {
		this.departmentServicesImpl.deleteDepartmentId(departmentDTO);
	}

}
