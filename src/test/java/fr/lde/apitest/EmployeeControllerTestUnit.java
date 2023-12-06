// package fr.lde.apitest;

// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;

// import fr.lde.apitest.controler.EmployeeController;
// import fr.lde.apitest.service.EmployeeService;

// @ActiveProfiles("test")
// @WebMvcTest(controllers = EmployeeController.class)
// public class EmployeeControllerTestUnit {

// @Autowired
// private MockMvc mockMvc;

// @MockBean
// private EmployeeService employeeService;

// @Test
// public void testGetEmployees() throws Exception {
// mockMvc.perform(get("/api/employees")).andExpect(
// status().is(200));
// }

// }
