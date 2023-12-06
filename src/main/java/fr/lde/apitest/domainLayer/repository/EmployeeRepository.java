package fr.lde.apitest.domainLayer.repository;

import java.util.Optional;

// import org.hibernate.annotations.processing.SQL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lde.apitest.domainLayer.model.Employee;

@Repository
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {

    public Optional<Employee> findByEmail(String email);

    // @SQL("SELECT * FROM Employee")
    // public Employee myPersonal(String email);

}