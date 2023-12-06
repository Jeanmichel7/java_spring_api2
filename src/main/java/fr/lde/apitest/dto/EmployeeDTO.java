package fr.lde.apitest.dto;

// import fr.lde.apitest.domaine.model.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// @Data
@Getter
@Setter
public class EmployeeDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;

  // public EmployeeDTO convertToDTO(Employee employee) {
  // this.id = employee.getId();
  // this.firstName = employee.getFirstName();
  // this.lastName = employee.getLastName();
  // this.email = employee.getEmail();
  // return this;
  // }
}
