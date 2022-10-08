package com.udacity.jdnd.course3.critter.controllers.converters.employee;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import java.util.function.Function;
import org.springframework.beans.BeanUtils;

public class FromEmployeeDtoToEntity implements Function<EmployeeDTO, Employee> {

  @Override
  public Employee apply(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDTO, employee);
    employee.setSkills(employeeDTO.getSkills());
    employee.setDaysAvailable(employeeDTO.getDaysAvailable());
    return employee;
  }
}
