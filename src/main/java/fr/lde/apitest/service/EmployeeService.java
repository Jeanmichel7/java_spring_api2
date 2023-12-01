package fr.lde.apitest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.lde.apitest.model.Employee;
import fr.lde.apitest.repository.EmployeeRepository;
import lombok.Data;
// import lombok.extern.slf4j.Slf4j;

@Data
@Service
// @Slf4j
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Iterable<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  public ResponseEntity<?> getEmployeeById(Long id) {
    Optional<Employee> employeeFound = employeeRepository.findById(id);

    // log.info("test log : " + employeeFound.get());
    // System.out.println("test sysout : " + employeeFound.get());

    if (employeeFound.isPresent())
      return ResponseEntity.ok(employeeFound.get());
    else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
    // return ResponseEntity.notFound().build();
  }

  // public ResponseEntity<Employee> getEmployee(final Long id) {
  // return employeeRepository.findById(id);
  // }

  public Employee saveEmployee(Employee employee) {
    Employee savedEmployee = employeeRepository.save(employee);
    return savedEmployee;
  }

  // public ResponseEntity<Employee> patchEmployee(Long id, Employee employeeData)
  // {
  // ResponseEntity<Employee> employeeFound = getEmployee(id);

  // return employeeFound.map(employee -> {
  // if (employeeData.getFirstName() != null) {
  // employee.setFirstName(employeeData.getFirstName());
  // }
  // if (employeeData.getLastName() != null) {
  // employee.setLastName(employeeData.getLastName());
  // }
  // if (employeeData.getMail() != null) {
  // employee.setMail(employeeData.getMail());
  // }
  // if (employeeData.getPassword() != null) {
  // employee.setPassword(employeeData.getPassword());
  // }
  // Employee updatedEmployee = this.saveEmployee(employee);
  // return ResponseEntity.ok(updatedEmployee);
  // }).orElseGet(() -> ResponseEntity.notFound().build());
  // }

  // public Employee patchEmployee(Long id, Employee employeeData) {
  // // ResponseEntity<Employee> tets;
  // Optional<Employee> employeeFound = getEmployeeById(id);

  // if (employeeFound.isPresent()) {
  // Employee employeeToUpdate = employeeFound.get();

  // String firstName = employeeData.getFirstName();
  // if (firstName != null)
  // employeeToUpdate.setFirstName(firstName);

  // String lastName = employeeData.getLastName();
  // if (lastName != null)
  // employeeToUpdate.setLastName(lastName);

  // String mail = employeeData.getMail();
  // if (mail != null)
  // employeeToUpdate.setMail(mail);

  // String password = employeeData.getPassword();
  // if (password != null)
  // employeeToUpdate.setPassword(password);

  // return this.saveEmployee(employeeToUpdate);
  // } else {
  // return null;
  // }
  // }

  public void deleteEmployee(final Long id) {
    employeeRepository.deleteById(id);
  }

}
