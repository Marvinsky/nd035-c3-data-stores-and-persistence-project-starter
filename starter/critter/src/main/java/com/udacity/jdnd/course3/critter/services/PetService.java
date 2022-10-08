package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import java.util.List;

public interface PetService {

  Pet savePet(Pet pet);
  Pet getPetById(Long id);
  List<Pet> getAllPets();
  List<Pet> getPetsByOwner(Long ownerId);
  boolean isPetInDB(Pet pet, Customer customer);
  List<Pet> findAllById(List<Long> petIds);
}
