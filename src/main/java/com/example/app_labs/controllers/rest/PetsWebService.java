package com.example.app_labs.controllers.rest;

import com.example.app_labs.dtos.NewPet;
import com.example.app_labs.entities.Household;
import com.example.app_labs.entities.Pet;
import com.example.app_labs.services.HouseholdService;
import com.example.app_labs.services.PetService;
import com.example.app_labs.services.exceptions.BadDataException;
import com.example.app_labs.services.exceptions.NonexistentRecordException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pets")
public class PetsWebService {
    private PetService petService;
    private HouseholdService householdService;

    @GetMapping({"/", ""})
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping({"/{id}"})
    public Pet getPetById(@PathVariable("id") int id) throws NonexistentRecordException {
        return petService.getPetById(id);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable("id") int id) throws NonexistentRecordException {
        petService.deletePetById(id);
    }

    @PostMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Pet addPet(@RequestBody @Valid NewPet newPet)
            throws BadDataException, NonexistentRecordException {
        Household household = null;
        if (newPet.householdEircode() != null && !newPet.householdEircode().isEmpty()) {
            household = householdService.findHouseholdByEircode(newPet.householdEircode());
        }
        Pet pet = new Pet(newPet.name(), newPet.animalType(), newPet.breed(),
                newPet.age(), household);
        petService.createPet(pet);
        return pet;
    }

    @PatchMapping({"/{id}/{newName}"})
    public void changePetName(@PathVariable int id, @PathVariable String newName) throws NonexistentRecordException, BadDataException {
        Pet pet = petService.getPetById(id);
        pet.setName(newName);
        petService.updatePet(pet);
    }








}
