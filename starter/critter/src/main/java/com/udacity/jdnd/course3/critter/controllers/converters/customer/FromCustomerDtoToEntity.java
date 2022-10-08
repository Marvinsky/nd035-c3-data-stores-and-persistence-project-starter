package com.udacity.jdnd.course3.critter.controllers.converters.customer;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import java.util.function.Function;
import org.springframework.beans.BeanUtils;

public class FromCustomerDtoToEntity implements Function<CustomerDTO, Customer> {

  @Override
  public Customer apply(CustomerDTO customerDTO) {
    Customer customer = new Customer();
    BeanUtils.copyProperties(customerDTO, customer);
    return customer;
  }
}
