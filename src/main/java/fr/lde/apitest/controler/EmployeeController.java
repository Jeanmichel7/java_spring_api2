package fr.lde.apitest.controler;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.lde.apitest.model.Employee;
import fr.lde.apitest.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Read - Get all employees
     * 
     * @return - An Iterable object of Employee full filled
     */
    // @GetMapping("/employees")
    // public ResponseEntity<Iterable<Employee>> getEmployees() {
    // return employeeService.getEmployees()
    // .map(ResponseEntity::ok)
    // .orElseThrow(() -> new EntityNotFoundException(
    // "Employee not found with ID: " + id));
    // }

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        Iterable<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
        // .map(ResponseEntity::ok)
        // .orElseThrow(() -> new EntityNotFoundException(
        // "Employee not found with ID: " + id));
        // .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @GetMapping(path = "/employee/{id}"
    // // params = "id=1"
    // )
    // public ResponseEntity<Employee> getEmployee(@PathVariable("id") final Long
    // id) {
    // Optional<Employee> employee = employeeService.getEmployee(id);
    // if (employee.isPresent()) {
    // return employee.get();
    // } else {
    // return null;
    // }
    // }

    // @PostMapping(path = "/employee", produces = "application/json")
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        System.out.println("test : " + employee);
        return employeeService.saveEmployee(employee);
    }

    // @PatchMapping("/employee/{id}")
    // public Employee patchEmployee(
    // @PathVariable("id") final Long id,
    // @RequestBody Employee employee) {
    // Employee employeUpdate = employeeService.patchEmployee(id, employee);
    // return employeUpdate;
    // }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(
            @PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

}