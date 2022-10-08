package com.udacity.jdnd.course3.critter.controllers.converters.customer;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

public class FromEntityToCustomerDto implements Function<Customer, CustomerDTO> {

  @Override
  public CustomerDTO apply(Customer customer) {
    CustomerDTO customerDTO = new CustomerDTO();
    BeanUtils.copyProperties(customer, customerDTO);
    try {
      customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getId())
          .collect(Collectors.toList()));
    } catch (Exception e) {
      System.out.println("Customer " + customer.getId() + " does not have any pet");
    }
    return customerDTO;
  }
}
