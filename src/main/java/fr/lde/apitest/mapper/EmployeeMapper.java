package fr.lde.apitest.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.lde.apitest.domaine.model.Employee;
import fr.lde.apitest.dto.EmployeeDTO;
import fr.lde.apitest.validator.CreateEmployeeDTO;
import fr.lde.apitest.validator.UpdateEmployeeDTO;

@Mapper
public interface EmployeeMapper {

  @Mapping(target = "")
  EmployeeDTO toDto(Employee employee);

  @InheritInverseConfiguration
  Employee toEntity(EmployeeDTO employeeDTO);

  @Mapping(target = "")
  Employee toEntity(CreateEmployeeDTO employeeDTO);

  @Mapping(target = "")
  Employee toEntity(UpdateEmployeeDTO employeeDTO);
}
