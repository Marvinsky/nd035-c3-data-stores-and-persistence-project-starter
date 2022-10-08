package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.controllers.converters.pet.FromPetDtoToEntity;
import com.udacity.jdnd.course3.critter.controllers.converters.pet.FromPetDtoToEntity.ConvertFromPetDtoAndCustomerToPet;
import com.udacity.jdnd.course3.critter.controllers.converters.pet.FromEntityToPetDto;
import com.udacity.jdnd.course3.critter.controllers.converters.pet.FromEntityToPetDto.ConvertFromPetAndOwnerIdToPetDto;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    Function<ConvertFromPetDtoAndCustomerToPet, Pet> fromPetDtoToEntity = new FromPetDtoToEntity();
    Function<ConvertFromPetAndOwnerIdToPetDto, PetDTO> fromEntityToPetDto = new FromEntityToPetDto();

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        Pet savedPet = petService.savePet(fromPetDtoToEntity.apply(ConvertFromPetDtoAndCustomerToPet.builder()
                .petDTO(petDTO)
                .customer(customer)
            .build()));
        return fromEntityToPetDto.apply(ConvertFromPetAndOwnerIdToPetDto.builder()
                .pet(savedPet)
                .ownerId(customer.getId())
            .build());
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        PetDTO petDTO = fromEntityToPetDto.apply(ConvertFromPetAndOwnerIdToPetDto.builder()
            .pet(pet)
            .ownerId(pet.getCustomer().getId())
            .build());
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAllPets().stream()
            .map(pet -> fromEntityToPetDto.apply(ConvertFromPetAndOwnerIdToPetDto.builder()
                    .pet(pet)
                    .ownerId(pet.getCustomer().getId())
                .build())).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwner(ownerId).stream()
            .map(pet -> fromEntityToPetDto.apply(ConvertFromPetAndOwnerIdToPetDto.builder()
                .pet(pet)
                .ownerId(pet.getCustomer().getId())
                .build())).collect(Collectors.toList());
    }
}
