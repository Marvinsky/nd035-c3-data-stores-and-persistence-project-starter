package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.controllers.converters.schedule.FromListEntityToListScheduleDto;
import com.udacity.jdnd.course3.critter.controllers.converters.schedule.FromListEntityToListScheduleDto.ConvertListSchedulesToListSchedulesDtos;
import com.udacity.jdnd.course3.critter.controllers.converters.schedule.FromScheduleDtoToEntity;
import com.udacity.jdnd.course3.critter.controllers.converters.schedule.FromScheduleDtoToEntity.ConvertScheduleDtoAndEmployeesAndPetsToSchedule;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PetService petService;

    Function<ConvertScheduleDtoAndEmployeesAndPetsToSchedule, Schedule> fromScheduleDtoToEntity = new FromScheduleDtoToEntity();
    Function<ConvertListSchedulesToListSchedulesDtos, List<ScheduleDTO>> fromListEntityToListScheduleDto = new FromListEntityToListScheduleDto();


    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        List<Employee> employees = customerService.findAllById(scheduleDTO.getEmployeeIds());
        List<Pet> pets = petService.findAllById(scheduleDTO.getPetIds());
        Schedule schedule = scheduleService.saveSchedule(fromScheduleDtoToEntity.apply(
            ConvertScheduleDtoAndEmployeesAndPetsToSchedule.builder()
                .scheduleDTO(scheduleDTO)
                .employees(employees)
                .pets(pets)
                .build()
        ));

        employees.forEach(employee -> {
            if (employee.getSchedules() == null) {
                employee.setSchedules(new ArrayList<>());
            } else {
                employee.getSchedules().add(schedule);
            }
        });

        pets.forEach(pet -> {
            if (pet.getSchedules() == null) {
                pet.setSchedules(new ArrayList<>());
            } else {
                pet.getSchedules().add(schedule);
            }
        });
        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return fromListEntityToListScheduleDto.apply(
            ConvertListSchedulesToListSchedulesDtos.builder()
                .schedules(scheduleService.getAllSchedule())
                .build()
        );
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return fromListEntityToListScheduleDto.apply(
            ConvertListSchedulesToListSchedulesDtos.builder()
                .schedules(scheduleService.getScheduleForPet(petId))
                .build());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return fromListEntityToListScheduleDto.apply(
            ConvertListSchedulesToListSchedulesDtos.builder()
                .schedules(scheduleService.getScheduleForEmployee(employeeId))
                .build());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

        List<Schedule> scheduleList = new ArrayList<>();
        for (Pet pet: customerService.getCustomerById(customerId).getPets()) {
            scheduleList.addAll(scheduleService.getScheduleForCustomer(pet.getId()));
        }

        return fromListEntityToListScheduleDto.apply(
            ConvertListSchedulesToListSchedulesDtos.builder()
                .schedules(scheduleList)
                .build());
    }
}
