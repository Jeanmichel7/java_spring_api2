package fr.lde.apitest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lde.apitest.model.Employee;

@Repository
public interface EmployeeRepository
    extends CrudRepository<Employee, Long> {

}