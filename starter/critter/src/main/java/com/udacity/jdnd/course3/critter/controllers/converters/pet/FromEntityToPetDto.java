package com.udacity.jdnd.course3.critter.controllers.converters.pet;

import com.udacity.jdnd.course3.critter.controllers.converters.pet.FromEntityToPetDto.ConvertFromPetAndOwnerIdToPetDto;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import java.util.function.Function;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public class FromEntityToPetDto implements Function<ConvertFromPetAndOwnerIdToPetDto, PetDTO> {

  @Override
  public PetDTO apply(ConvertFromPetAndOwnerIdToPetDto t) {
    PetDTO petDTO = new PetDTO();
    BeanUtils.copyProperties(t.getPet(), petDTO);
    petDTO.setOwnerId(t.getOwnerId());
    return petDTO;
  }

  @Builder
  @Data
  public static class ConvertFromPetAndOwnerIdToPetDto {
    private Pet pet;
    private Long ownerId;
  }

}
