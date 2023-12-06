package fr.lde.apitest.dto;

import fr.lde.apitest.domaine.model.Employee;
import lombok.Data;

@Data
public class EmployeeResponseDTO {
  private String firstName;
  private String lastName;
  private String email;

  public EmployeeResponseDTO convertToDTO(Employee employee) {
    this.firstName = employee.getFirstName();
    this.lastName = employee.getLastName();
    this.email = employee.getEmail();
    return this;
  }
}
