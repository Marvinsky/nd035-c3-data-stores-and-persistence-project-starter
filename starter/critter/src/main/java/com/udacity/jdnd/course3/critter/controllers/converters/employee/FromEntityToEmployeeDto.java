package com.udacity.jdnd.course3.critter.controllers.converters.employee;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import java.util.function.Function;
import org.springframework.beans.BeanUtils;

public class FromEntityToEmployeeDto implements Function<Employee, EmployeeDTO> {

  @Override
  public EmployeeDTO apply(Employee employee) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    BeanUtils.copyProperties(employee, employeeDTO);
    return employeeDTO;
  }
}
