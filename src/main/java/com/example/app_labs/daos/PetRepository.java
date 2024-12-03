package com.example.app_labs.daos;

import com.example.app_labs.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    int deletePetById(Long id);
    int deletePetByName(String name);
    List<Pet> findPetsByBreed(String breed);
    List<Pet> findPetsByAnimalType(String animalType);

    @Modifying
    @Transactional
    @Query("UPDATE Pet p SET p.name=:name, p.age=:age, p.animalType=:animalType, p.breed=:breed WHERE p.id=:id")
    int updatePetById(@Param("id") long id, @Param("name") String name, @Param("age") int age, @Param("animalType") String animalType, @Param("breed") String breed);

    @Query("SELECT avg(age) from Pet ")
    float findAverageAge();

    @Query("SELECT max(age) from Pet ")
    int findMaxAge();
}
