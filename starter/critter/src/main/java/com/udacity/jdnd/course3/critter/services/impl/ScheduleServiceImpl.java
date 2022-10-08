package com.udacity.jdnd.course3.critter.services.impl;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  ScheduleRepository scheduleRepository;
  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  PetRepository petRepository;

  @Override
  public Schedule saveSchedule(Schedule schedule) {
    return scheduleRepository.save(schedule);
  }

  @Override
  public List<Schedule> getAllSchedule() {
    return scheduleRepository.findAll();
  }

  @Override
  public List<Schedule> getScheduleForPet(Long petId) {
    return scheduleRepository.findSchedulesByPet(petRepository.getOne(petId));
  }

  @Override
  public List<Schedule> getScheduleForEmployee(Long employeeId) {
    return scheduleRepository.findSchedulesByEmployee(employeeRepository.getOne(employeeId));
  }

  @Override
  public List<Schedule> getScheduleForCustomer(Long petId) {
    return scheduleRepository.findSchedulesByPetId(petId);
  }
}
