package com.example.app_labs.services;

import com.example.app_labs.dtos.PetNameTypeBreed;
import com.example.app_labs.entities.Pet;
import com.example.app_labs.daos.PetRepository;
import com.example.app_labs.services.exceptions.BadDataException;
import com.example.app_labs.services.exceptions.NonexistentRecordException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class PetServiceImpl implements PetService {
    private PetRepository petRepository;

    @Override
    public void createPet(Pet pet) throws BadDataException {
        List<String> validAnimalTypes = Arrays.asList("Dog", "Cat", "Rabbit", "Fish", "Bird", "Hamster");
        if (validAnimalTypes.contains(pet.getAnimalType())){
            petRepository.save(pet);
        } else {
            throw new BadDataException("Invalid animal type");
        }

    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(long id) throws NonexistentRecordException {
        return petRepository.findById(id).orElseThrow(
                () -> new NonexistentRecordException("Pet not found ID: " + id)
        );
    }

    @Override
    public void updatePet(Pet pet) throws BadDataException {
        List<String> validAnimalTypes = Arrays.asList("Dog", "Cat", "Rabbit", "Fish", "Bird", "Hamster");
        if (validAnimalTypes.contains(pet.getAnimalType())){
            petRepository.updatePetById(pet.getId(), pet.getName(), pet.getAge(), pet.getAnimalType(), pet.getBreed());
        } else {
            throw new BadDataException("Invalid animal type");
        }

    }

    @Override
    public void deletePetById(long id) throws NonexistentRecordException {
        int rowsAffected = petRepository.deletePetById(id);
        if (rowsAffected != 1) {
            throw new NonexistentRecordException("Pet not found ID: " + id);
        }
    }

    @Override
    public void deletePetByName(String name) throws NonexistentRecordException {
        int rowsAffected = petRepository.deletePetByName(name);
        if (rowsAffected != 1) {
            throw new NonexistentRecordException("Pet not found name: " + name);
        }
    }

    @Override
    public List<Pet> getPetsByType(String type) {
        return petRepository.findPetsByAnimalType(type);
    }

    @Override
    public List<Pet> getPetsByBreed(String breed) {
        return petRepository.findPetsByBreed(breed);
    }

    @Override
    public List<PetNameTypeBreed> getPetsNameTypeBreed() {
        List<Pet> pet_list = petRepository.findAll();
        return pet_list.stream().map(pet -> new PetNameTypeBreed(pet.getName(), pet.getAnimalType(), pet.getBreed())).toList();
    }

    @Override
    public float getAverageAge() {
        return petRepository.findAverageAge();
    }

    @Override
    public int getOldestAge() {
        return petRepository.findMaxAge();
    }


}
