package com.udacity.jdnd.course3.critter.services.impl;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;


  @Override
  public Employee saveEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public Employee getEmployeeById(Long employeeId) {
    return employeeRepository.findById(employeeId).orElseThrow(NotFoundException::new);
  }

  @Override
  public Employee updateEmployee(Set<DayOfWeek> daysAvailable, Long id) {
    Employee employee = new Employee();
    if (employeeRepository.findById(id).isPresent()) {
      employee = employeeRepository.findById(id).get();
      employee.setDaysAvailable(daysAvailable);
      employeeRepository.save(employee);
    } else {
      throw new NotFoundException();
    }
    return employee;
  }

  @Override
  public List<Employee> getEmployeeForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
    List<Employee> employees = employeeRepository.findAllByDaysAvailableContaining(dayOfWeek);
    return employees.stream().filter(e -> e.getSkills().containsAll(skills))
        .collect(Collectors.toList());
  }
}
