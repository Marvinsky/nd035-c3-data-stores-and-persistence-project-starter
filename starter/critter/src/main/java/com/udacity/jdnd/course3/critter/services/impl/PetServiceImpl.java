package com.udacity.jdnd.course3.critter.services.impl;


import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.services.PetService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetServiceImpl implements PetService {

  @Autowired
  PetRepository petRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public Pet savePet(Pet pet) {
    Pet savedPet = petRepository.save(pet);
    Customer  customer = savedPet.getCustomer();
    List<Pet> customerPets = customer.getPets();
    if (customerPets == null) {
      customerPets = new ArrayList<>();
    }
    customerPets.add(savedPet);
    customer.setPets(customerPets);
    customerRepository.save(customer);
    return savedPet;
  }

  @Override
  public Pet getPetById(Long id) {
    return petRepository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found"));
  }

  @Override
  public List<Pet> getAllPets() {
    List<Pet> petList = petRepository.findAll();
    if (petList.size() == 0) {
      throw  new NotFoundException("Pet list is zero");
    }
    return petList;
  }

  @Override
  public List<Pet> getPetsByOwner(Long ownerId) {
    return petRepository.getPetsByCustomerId(ownerId);
  }

  @Override
  public boolean isPetInDB(Pet pet, Customer customer) {
    List<Pet> pets = customer.getPets();
    if (pets.size() == 0) {
      return false;
    }
    for (Pet myPet: pets) {
      if (myPet.getName().equalsIgnoreCase(pet.getName()) &&
        myPet.getType().equals(pet.getType()) &&
          myPet.getBirthDate().equals(pet.getBirthDate())
      ) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<Pet> findAllById(List<Long> petIds) {
    return petRepository.findAllById(petIds);
  }
}
