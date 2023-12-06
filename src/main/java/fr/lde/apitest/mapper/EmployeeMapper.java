package fr.lde.apitest.mapper;

// import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

import fr.lde.apitest.domain.model.Employee;
import fr.lde.apitest.dto.EmployeeDTO;
import fr.lde.apitest.validator.CreateEmployeeDTO;
import fr.lde.apitest.validator.UpdateEmployeeDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDTO toDto(Employee employee);

  Employee toEntity(EmployeeDTO employeeDTO);

  Employee toEntity(CreateEmployeeDTO employeeDTO);

  Employee toEntity(UpdateEmployeeDTO employeeDTO);
}