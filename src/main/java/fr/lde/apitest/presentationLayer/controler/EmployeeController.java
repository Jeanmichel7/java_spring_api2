package fr.lde.apitest.presentationLayer.controler;

import java.util.List;
import java.util.ArrayList;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.lde.apitest.businessLayer.service.EmployeeService;
import fr.lde.apitest.domainLayer.model.Employee;
import fr.lde.apitest.presentationLayer.dto.EmployeeDTO;
import fr.lde.apitest.presentationLayer.mapper.EmployeeMapper;
import fr.lde.apitest.presentationLayer.validator.CreateEmployeeDTO;
import fr.lde.apitest.presentationLayer.validator.UpdateEmployeeDTO;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api")
public class EmployeeController {

	private EmployeeService employeeService;
	private EmployeeMapper mapper;

	public EmployeeController(
			EmployeeService employeeService,
			EmployeeMapper mapper) {
		this.employeeService = employeeService;
		this.mapper = mapper;
	}

	@GetMapping("/employees")
	@ResponseBody
	public List<EmployeeDTO> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();

		List<EmployeeDTO> er = new ArrayList<>();
		for (Employee employee : employees)
			er.add(mapper.toDto(employee));
		return er;
	}

	@GetMapping("/employee/{id}")
	@ResponseBody
	public EmployeeDTO getEmployee(@PathVariable final Long id) {
		return mapper.toDto(employeeService.getEmployeeById(id));
	}

	@PostMapping("/employee")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public EmployeeDTO createEmployee(
			@Valid @RequestBody CreateEmployeeDTO createEmployeeDTO) {
		return mapper.toDto(employeeService.saveEmployee(mapper.toEntity(createEmployeeDTO)));
	}

	@ResponseBody
	@PatchMapping("/employee/{id}")
	public EmployeeDTO patchEmployee(
			@PathVariable final Long id,
			@Valid @RequestBody UpdateEmployeeDTO employeeDTO) {
		return mapper.toDto(employeeService.patchEmployee(
				id,
				mapper.toEntity(employeeDTO)));
	}

	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(
			@PathVariable("id") final Long ID) {
		employeeService.deleteEmployee(ID);
	}

}