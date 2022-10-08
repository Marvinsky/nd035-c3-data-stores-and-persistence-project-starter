package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import java.util.List;

public interface ScheduleService {
  Schedule saveSchedule(Schedule schedule);
  List<Schedule> getAllSchedule();
  List<Schedule> getScheduleForPet(Long petId);
  List<Schedule> getScheduleForEmployee(Long employeeId);
  List<Schedule> getScheduleForCustomer(Long petId);
}
