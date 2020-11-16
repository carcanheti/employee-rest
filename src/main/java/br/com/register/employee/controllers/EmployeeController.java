package br.com.register.employee.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.register.employee.dto.EmployeeDTO;
import br.com.register.employee.dto.EmployeeOfDepartmentDTO;
import br.com.register.employee.dto.DataListOutputDTO;
import br.com.register.employee.dto.DataOutputDTO;
import br.com.register.employee.dto.DepartmentDTO;
import br.com.register.employee.dto.NewEmployeeDTO;
import br.com.register.employee.entities.Employee;
import br.com.register.employee.entities.EmployeeOfDepartment;
import br.com.register.employee.exceptions.RegisterNotFoundException;
import br.com.register.employee.models.ErrorResponse;
import br.com.register.employee.services.impl.EmployeeServicesImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Funcionário")
@RestController
@RequestMapping(value = "/employee/parameters")
public class EmployeeController {
	
	private EmployeeServicesImpl employeeServicesImpl;

	public EmployeeController(EmployeeServicesImpl employeeServicesImpl) {
		this.employeeServicesImpl = employeeServicesImpl;
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar funcionários")
	@GetMapping
	public ResponseEntity<DataListOutputDTO<EmployeeDTO>> findAll() {

		DataListOutputDTO<EmployeeDTO> list = this.employeeServicesImpl.findAllEmployees();

		return ResponseEntity.ok(list);
	}
	
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
		@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
		@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Consultar funcionários por departamento")
	@GetMapping(value = "/department")
	public ResponseEntity<DataListOutputDTO<EmployeeDTO>> findEmployeesByDepartment(
			@RequestBody DepartmentDTO departmentDTO) {
	
		DataListOutputDTO<EmployeeDTO> list = this.employeeServicesImpl.findEmployeesByDepartment(departmentDTO);
	
		return ResponseEntity.ok(list);
	}
	

	@ApiResponses({ @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Salvar funcionário")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataOutputDTO<EmployeeDTO>> saveEmployee(@RequestBody NewEmployeeDTO newEmployeeDTO) {

		DataOutputDTO<EmployeeDTO> data = new DataOutputDTO<>();
		Optional<Employee> op = this.employeeServicesImpl.createEmployee(newEmployeeDTO);

		if (op.isPresent()) {

			EmployeeDTO dto = op.get().toDTO();
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
	@ApiOperation("Alterar cadastro de funcionário")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		this.employeeServicesImpl.updateEmployeeById(employeeDTO);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Deletar funcionários")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@RequestBody EmployeeDTO employeeDTO) {
		this.employeeServicesImpl.deleteEmployeeById(employeeDTO);
	}

	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
		@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
		@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Alterar departamento de funcionário")
	@PutMapping(value = "/modifiy-departament")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDepartmentByEmployee(@RequestBody EmployeeDTO employeeDTO, @RequestBody DepartmentDTO departmentDTO) {
		this.employeeServicesImpl.updateDepartmentByEmployee(employeeDTO, departmentDTO); 
	}
	
	
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "BadRequest", response = ErrorResponse.class),
		@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
		@ApiResponse(code = 500, message = "InternalServerError", response = ErrorResponse.class) })
	@ApiOperation("Cadastrar funcionário a um departamento")
	@PostMapping(value = "/employee-of-department")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataOutputDTO<EmployeeOfDepartmentDTO>> saveEmployeeByDepartment(@RequestBody EmployeeOfDepartmentDTO employeeOfDepartmentDTO) {
	
		DataOutputDTO<EmployeeOfDepartmentDTO> data = new DataOutputDTO<>();
		Optional<EmployeeOfDepartment> op = this.employeeServicesImpl.saveEmployeeByDepartment(employeeOfDepartmentDTO);
	
		if (op.isPresent()) {
	
			EmployeeOfDepartmentDTO dto = op.get().toDTO();
			data.setData(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(data);
		}
	
		return ResponseEntity.status(HttpStatus.CREATED).body(data);
	}
	
		
	

}
