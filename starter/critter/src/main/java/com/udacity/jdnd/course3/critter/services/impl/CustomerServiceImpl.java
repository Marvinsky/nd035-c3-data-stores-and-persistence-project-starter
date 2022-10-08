package com.udacity.jdnd.course3.critter.services.impl;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public List<Customer> getAllCustomers() {
    List<Customer> list = customerRepository.findAll();
    if (list.size() == 0) {
      throw new NotFoundException("Customer table is empty!");
    }
    return list;
  }

  @Override
  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).orElseThrow(NotFoundException::new);
  }

  @Override
  public List<Employee> findAllById(List<Long> employeeId) {
    return employeeRepository.findAllById(employeeId);
  }

  @Override
  public Employee getEmployeeById(Long employeeId) {
    return employeeRepository.findById(employeeId).orElseThrow(NotFoundException::new);
  }
}
