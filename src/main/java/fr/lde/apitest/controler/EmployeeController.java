package fr.lde.apitest.controler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.lde.apitest.model.Employee;
import fr.lde.apitest.service.EmployeeService;

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
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/employee/{id}"
    // params = "id=1"
    )
    public Employee getEmployee(@PathVariable("id") final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    // @PostMapping(path = "/employee", produces = "application/json")
    @PostMapping(path = "/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        System.out.println("test : " + employee);
        return employeeService.saveEmployee(employee);
    }

}