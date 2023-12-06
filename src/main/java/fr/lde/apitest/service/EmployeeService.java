package fr.lde.apitest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.lde.apitest.domaine.model.Employee;
import fr.lde.apitest.domaine.repository.EmployeeRepository;
import fr.lde.apitest.exception.ConflitException;
import fr.lde.apitest.exception.TestException;
import fr.lde.apitest.exception.UserNotFoundException;
import fr.lde.apitest.validator.CreateEmployeeDTO;
import lombok.Data;

// import org.modelmapper.ModelMapper;
// import lombok.extern.slf4j.Slf4j;

@Data
@Service
// @Slf4j
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Optional<Iterable<Employee>> getEmployees() {
    Optional<Iterable<Employee>> employees = Optional.ofNullable(employeeRepository.findAll());
    return employees;
  }

  // public Iterable<Employee> getAllEmployees() {
  // return employeeRepository.findAll();
  // }

  public Employee getEmployeeById(Long id) throws UserNotFoundException, TestException {
    Optional<Employee> employeeFound = employeeRepository.findById(id);

    // return employeeRepository.findById(id)
    // .orElseThrow(() -> new UserNotFoundException("Employee not found with ID: " +
    // id));

    if (employeeFound.isPresent()) {
      Employee employee = employeeFound.get();
      if (employee.getId() == 4)
        throw new TestException();
      return employee;
    } else
      throw new UserNotFoundException(id);
  }

  public Employee saveEmployee(CreateEmployeeDTO employeeDTO) throws ConflitException {

    Employee employee = new Employee();
    employee.setFirstName(employeeDTO.getFirstName());
    employee.setLastName(employeeDTO.getLastName());
    employee.setEmail(employeeDTO.getEmail());
    employee.setPassword(employeeDTO.getPassword());

    Optional<Employee> employeeFound = employeeRepository.findByEmail(employee.getEmail());
    if (employeeFound.isPresent())
      throw new ConflitException("Employee already exist with mail: " + employee.getEmail());

    Employee savedEmployee = employeeRepository.save(employee);
    return savedEmployee;
  }

  public Employee patchEmployee(Long id, Employee employeeData)
      throws UserNotFoundException, ConflitException {
    System.out.println("employeeData : " + employeeData);

    Optional<Employee> employeeFound = employeeRepository.findById(id);
    if (!employeeFound.isPresent())
      throw new UserNotFoundException(id);

    Employee employeeToUpdated = employeeFound.get();
    // System.out.println("employee updated : " + employeeToUpdated);

    String firstName = employeeData.getFirstName();
    if (firstName != null)
      employeeToUpdated.setFirstName(firstName);

    String lastName = employeeData.getLastName();
    if (lastName != null)
      employeeToUpdated.setLastName(lastName);

    String mail = employeeData.getEmail();
    if (mail != null) {
      Optional<Employee> employeeFoundByMail = employeeRepository.findByEmail(mail);
      if (employeeFoundByMail.isPresent())
        throw new ConflitException("Email already used");
      employeeToUpdated.setEmail(mail);
    }

    String password = employeeData.getPassword();
    if (password != null)
      employeeToUpdated.setPassword(password);

    Employee employeeUpdated = employeeRepository.save(employeeToUpdated);
    return employeeUpdated;
  }

  public void deleteEmployee(final Long id) throws UserNotFoundException {
    Optional<Employee> employeeFound = employeeRepository.findById(id);

    if (!employeeFound.isPresent())
      throw new UserNotFoundException(id);

    employeeRepository.deleteById(id);
  }

}
