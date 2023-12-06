package fr.lde.apitest.businessLayer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lde.apitest.domainLayer.model.Employee;
import fr.lde.apitest.domainLayer.repository.EmployeeRepository;
import fr.lde.apitest.presentationLayer.exception.ConflitException;
import fr.lde.apitest.presentationLayer.exception.TestException;
import fr.lde.apitest.presentationLayer.exception.UserNotFoundException;
import lombok.Data;

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

  public Employee getEmployeeById(Long id)
      throws UserNotFoundException, TestException {
    Optional<Employee> employeeFound = employeeRepository.findById(id);

    if (!employeeFound.isPresent())
      throw new UserNotFoundException(id);

    Employee employee = employeeFound.get();
    if (employee.getId() == 4)
      throw new TestException();
    return employee;
  }

  public Employee saveEmployee(Employee employee)
      throws ConflitException {

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

  public void deleteEmployee(final Long id)
      throws UserNotFoundException {
    Optional<Employee> employeeFound = employeeRepository.findById(id);

    if (!employeeFound.isPresent())
      throw new UserNotFoundException(id);

    employeeRepository.deleteById(id);
  }
}
