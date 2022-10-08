package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import java.util.List;

public interface CustomerService {

  Customer saveCustomer(Customer customer);
  List<Customer> getAllCustomers();
  Customer getCustomerById(Long id);
  List<Employee> findAllById(List<Long> employeeId);
  Employee getEmployeeById(Long employeeId);

}
