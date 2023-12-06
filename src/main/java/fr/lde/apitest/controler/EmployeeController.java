package fr.lde.apitest.controler;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.lde.apitest.domaine.model.Employee;
import fr.lde.apitest.dto.EmployeeResponseDTO;
import fr.lde.apitest.service.EmployeeService;
import fr.lde.apitest.validator.CreateEmployeeDTO;
import jakarta.validation.Valid;

// import jakarta.persistence.EntityNotFoundException;

@RestController
@Validated
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<Iterable<EmployeeResponseDTO>> getEmployees() {
        Iterable<Employee> employees = employeeService.getEmployees().get();

        List<EmployeeResponseDTO> er = new ArrayList<>();
        for (Employee employee : employees) {
            er.add(new EmployeeResponseDTO().convertToDTO(employee));
        }
        return ResponseEntity.ok(er);

        // return employeeService.getEmployees()
        // .map(ResponseEntity::ok)
        // .orElseThrow(() -> new EntityNotFoundException(
        // "Employees not found"));
    }

    // @GetMapping("/employees")
    // public ResponseEntity<Iterable<Employee>> getAllEmployees() {
    // Iterable<Employee> employees = employeeService.getAllEmployees();
    // return ResponseEntity.ok(employees);
    // }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeResponseDTO r = new EmployeeResponseDTO().convertToDTO(employee);
        return ResponseEntity.ok(r);

        // return employeeService.getEmployeeById(id);
        // .map(ResponseEntity::ok)
        // .orElseThrow(() -> new EntityNotFoundException(
        // "Employee not found with ID: " + id));
        // .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody CreateEmployeeDTO employeeDTO) {
        // System.out.println("data recu : " + employeeDTO);
        Employee truc = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(truc);
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Employee> patchEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeData) {
        Employee employee = employeeService.patchEmployee(id, employeeData);
        return ResponseEntity.ok(employee);

    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(
            @PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

}