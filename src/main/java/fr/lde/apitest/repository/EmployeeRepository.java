package fr.lde.apitest.repository;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lde.apitest.model.Employee;

@Repository
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {

    // public Employee getByEmail(String email);

    // @SQL("SELECT * FROM Employee")
    // public Employee myPersonal(String email);

}