package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

  Employee saveEmployee(Employee employee);
  Employee getEmployeeById(Long employeeId);
  Employee updateEmployee(Set<DayOfWeek> daysAvailable, Long id);
  List<Employee> getEmployeeForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);
}
