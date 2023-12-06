package fr.lde.apitest.controler;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.lde.apitest.domaine.model.Employee;
import fr.lde.apitest.dto.EmployeeDTO;
import fr.lde.apitest.service.EmployeeService;
import fr.lde.apitest.validator.CreateEmployeeDTO;
import fr.lde.apitest.validator.UpdateEmployeeDTO;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

@RestController
@Validated
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseBody
    public List<EmployeeDTO> getEmployees() {
        Iterable<Employee> employees = employeeService.getEmployees().get();

        List<EmployeeDTO> er = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDto = modelMapper.map(employee, EmployeeDTO.class);
            er.add(employeeDto);
        }
        return er;
    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public EmployeeDTO getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDTO r = modelMapper.map(employee, EmployeeDTO.class);
        // EmployeeDTO r = new EmployeeDTO().convertToDTO(employee);
        return r;
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EmployeeDTO createEmployee(
            @Valid @RequestBody CreateEmployeeDTO employeeDTO) {
        Employee employeeToCreate = modelMapper.map(employeeDTO, Employee.class);
        Employee employeeCreated = employeeService.saveEmployee(employeeToCreate);
        EmployeeDTO employee = modelMapper.map(employeeCreated, EmployeeDTO.class);
        return employee;
    }

    @ResponseBody
    @PatchMapping("/employee/{id}")
    public EmployeeDTO patchEmployee(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeDTO employeeDataDTO) {
        Employee employeeToUpdate = modelMapper.map(employeeDataDTO, Employee.class);
        Employee updatedEmployee = employeeService.patchEmployee(id, employeeToUpdate);
        EmployeeDTO employeeDto = modelMapper.map(updatedEmployee, EmployeeDTO.class);
        return employeeDto;
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(
            @PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

}