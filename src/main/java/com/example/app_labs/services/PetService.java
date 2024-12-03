package com.example.app_labs.services;

import com.example.app_labs.dtos.PetNameTypeBreed;
import com.example.app_labs.entities.Pet;
import com.example.app_labs.services.exceptions.BadDataException;
import com.example.app_labs.services.exceptions.NonexistentRecordException;

import java.util.List;

public interface PetService {
    void createPet(Pet pet) throws BadDataException;
    List<Pet> getAllPets();
    Pet getPetById(long id) throws NonexistentRecordException;
    void updatePet(Pet pet) throws BadDataException;
    void deletePetById(long id) throws NonexistentRecordException;
    void deletePetByName(String name) throws NonexistentRecordException;
    List<Pet> getPetsByType(String type);
    List<Pet> getPetsByBreed(String breed);
    List<PetNameTypeBreed> getPetsNameTypeBreed();
    float getAverageAge();
    int getOldestAge();
}
