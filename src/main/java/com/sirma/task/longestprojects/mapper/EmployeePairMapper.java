package com.sirma.task.longestprojects.mapper;

import com.sirma.task.longestprojects.domain.EmployeePair;
import com.sirma.task.longestprojects.dto.EmployeePairDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel="spring")
public interface EmployeePairMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmployeePair updateEmployeeFromDto(EmployeePairDTO employeeDto, @MappingTarget EmployeePair employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmployeePairDTO updateDtoFromEmployee(EmployeePair employee, @MappingTarget EmployeePairDTO employeeDto);
}
