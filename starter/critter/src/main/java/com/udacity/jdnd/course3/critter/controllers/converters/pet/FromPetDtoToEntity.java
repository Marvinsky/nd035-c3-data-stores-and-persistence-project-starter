package com.udacity.jdnd.course3.critter.controllers.converters.pet;

import com.udacity.jdnd.course3.critter.controllers.converters.pet.FromPetDtoToEntity.ConvertFromPetDtoAndCustomerToPet;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import java.util.function.Function;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public class FromPetDtoToEntity implements Function<ConvertFromPetDtoAndCustomerToPet, Pet> {

  @Override
  public Pet apply(ConvertFromPetDtoAndCustomerToPet t) {
    Pet pet = new Pet();
    BeanUtils.copyProperties(t.getPetDTO(), pet);
    pet.setCustomer(t.getCustomer());
    return pet;
  }

  @Builder
  @Data
  public static class ConvertFromPetDtoAndCustomerToPet {
    private PetDTO petDTO;
    private Customer customer;
  }
}
