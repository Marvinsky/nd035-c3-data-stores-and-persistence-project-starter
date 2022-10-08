package com.udacity.jdnd.course3.critter.controllers.converters.schedule;

import com.udacity.jdnd.course3.critter.controllers.converters.schedule.FromScheduleDtoToEntity.ConvertScheduleDtoAndEmployeesAndPetsToSchedule;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import java.util.List;
import java.util.function.Function;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public class FromScheduleDtoToEntity implements Function<ConvertScheduleDtoAndEmployeesAndPetsToSchedule, Schedule> {

  @Override
  public Schedule apply(
      ConvertScheduleDtoAndEmployeesAndPetsToSchedule t) {
    Schedule schedule = new Schedule();
    BeanUtils.copyProperties(t.getScheduleDTO(), schedule);
    schedule.setEmployee(t.getEmployees());
    schedule.setPet(t.getPets());
    return schedule;
  }

  @Builder
  @Data
  public static class ConvertScheduleDtoAndEmployeesAndPetsToSchedule {
    private ScheduleDTO scheduleDTO;
    private List<Employee> employees;
    private List<Pet> pets;
  }

}
