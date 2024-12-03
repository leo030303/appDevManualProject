package com.example.app_labs.controllers.graphql;

import com.example.app_labs.entities.Household;
import com.example.app_labs.entities.Pet;
import com.example.app_labs.services.HouseholdService;
import com.example.app_labs.services.PetService;
import com.example.app_labs.services.exceptions.NonexistentRecordException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLService {
    private PetService petService;
    private HouseholdService householdService;

    @QueryMapping
    Pet findPetById(@Argument int petId) throws NonexistentRecordException {
        return petService.getPetById(petId);
    }
    @QueryMapping
    Household findHouseholdByEircode(@Argument String eircode) throws NonexistentRecordException {
        return householdService.findHouseholdByEircode(eircode);
    }

    @QueryMapping
    List<Household> findAllHouseholds() {
        return householdService.getAll();
    }

    @QueryMapping
    List<Pet> findAllPetsByAnimalType(String animalType) {
        return petService.getPetsByType(animalType);
    }

    @QueryMapping
    int getMaximumPetAge() {
        return petService.getOldestAge();
    }
    @QueryMapping
    float getAveragePetAge() {
        return petService.getAverageAge();
    }


    @MutationMapping
    int deletePet(@Argument int petId) throws NonexistentRecordException {
        petService.deletePetById(petId);
        return 1;
    }

    @MutationMapping
    int deleteHousehold(@Argument String eircode) throws NonexistentRecordException {
        householdService.deleteByEircode(eircode);
        return 1;
    }

    @MutationMapping
    Household createHousehold(@Valid @Argument("household") Household household) {
        householdService.createHousehold(household);
        return household;
    }
}

