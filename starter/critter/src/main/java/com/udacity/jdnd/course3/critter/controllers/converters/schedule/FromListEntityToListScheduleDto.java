package com.udacity.jdnd.course3.critter.controllers.converters.schedule;

import com.udacity.jdnd.course3.critter.controllers.converters.schedule.FromListEntityToListScheduleDto.ConvertListSchedulesToListSchedulesDtos;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public class FromListEntityToListScheduleDto implements Function<ConvertListSchedulesToListSchedulesDtos, List<ScheduleDTO>> {

  @Override
  public List<ScheduleDTO> apply(ConvertListSchedulesToListSchedulesDtos t) {
    List<ScheduleDTO> dtoList = new ArrayList<>();
    if (t.getSchedules() != null) {
      dtoList = t.getSchedules().stream().map(
          schedule -> {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);

            scheduleDTO.setEmployeeIds(schedule.getEmployee().stream()
                .map(Employee::getId).collect(Collectors.toList()));

            scheduleDTO.setPetIds(schedule.getPet().stream()
                .map(Pet::getId).collect(Collectors.toList()));
            return scheduleDTO;
          }
      ).collect(Collectors.toList());
    }
    return dtoList;
  }

  @Builder
  @Data
  public static class ConvertListSchedulesToListSchedulesDtos {
    private List<Schedule> schedules;
  }

}
