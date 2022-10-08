package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.controllers.converters.customer.FromCustomerDtoToEntity;
import com.udacity.jdnd.course3.critter.controllers.converters.employee.FromEmployeeDtoToEntity;
import com.udacity.jdnd.course3.critter.controllers.converters.customer.FromEntityToCustomerDto;
import com.udacity.jdnd.course3.critter.controllers.converters.employee.FromEntityToEmployeeDto;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    Function<CustomerDTO, Customer> fromCustomerDtoToEntity = new FromCustomerDtoToEntity();
    Function<Customer, CustomerDTO> fromEntityToCustomerDto = new FromEntityToCustomerDto();
    Function<EmployeeDTO, Employee> fromEmployeeDtoToEntity = new FromEmployeeDtoToEntity();
    Function<Employee, EmployeeDTO> fromEntityToEmployeeDto = new FromEntityToEmployeeDto();


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer savedCustomer = customerService.saveCustomer(fromCustomerDtoToEntity.apply(customerDTO));
        return fromEntityToCustomerDto.apply(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers().stream()
            .map(fromEntityToCustomerDto::apply).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.getPetById(petId);
        CustomerDTO customerDTO = fromEntityToCustomerDto.apply(customerService.getCustomerById(pet.getCustomer().getId()));
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = fromEmployeeDtoToEntity.apply(employeeDTO);
        return fromEntityToEmployeeDto.apply(employeeService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return fromEntityToEmployeeDto.apply(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.updateEmployee(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeService.getEmployeeForService(employeeDTO.getSkills(), employeeDTO.getDate()
            .getDayOfWeek()).stream()
            .map(fromEntityToEmployeeDto::apply).collect(Collectors.toList());
    }
}
