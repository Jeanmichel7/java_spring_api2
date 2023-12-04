package fr.lde.apitest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.lde.apitest.controler.EmployeeController;
import fr.lde.apitest.domaine.model.Employee;
import fr.lde.apitest.service.EmployeeService;

// @SpringBootTest
// @AutoConfigureMockMvc
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTestUnitaire {

  @Autowired
  public MockMvc mockMvc;

  @MockBean
  private EmployeeService employeeService;

  @Test
  public void testGetEmployees() throws Exception {
    // Mockito.when(employeeService.getEmployee(1L)).then(new Optinal<Employee>());
    mockMvc
        .perform(get("/api/employees"))
        .andExpect(status().isOk());
    // .andExpect(jsonPath(
    // "$[0].firstName", is("nametest")));
  }
}
