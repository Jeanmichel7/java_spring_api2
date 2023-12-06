package fr.lde.apitest.presentationLayer.mapper;

// import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

import fr.lde.apitest.domainLayer.model.Employee;
import fr.lde.apitest.presentationLayer.dto.EmployeeDTO;
import fr.lde.apitest.presentationLayer.validator.CreateEmployeeDTO;
import fr.lde.apitest.presentationLayer.validator.UpdateEmployeeDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDTO toDto(Employee employee);

  Employee toEntity(EmployeeDTO employeeDTO);

  Employee toEntity(CreateEmployeeDTO employeeDTO);

  Employee toEntity(UpdateEmployeeDTO employeeDTO);
}