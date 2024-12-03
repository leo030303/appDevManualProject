package com.example.app_labs;

import com.example.app_labs.entities.Pet;
import com.example.app_labs.repositories.PetRepository;
import com.example.app_labs.services.PetService;
import com.example.app_labs.services.PetServiceImpl;
import com.example.app_labs.services.exceptions.BadDataException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AppLabsApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(AppLabsApplication.class, args);
        PetService petService = context.getBean(PetServiceImpl.class);
        try {
            petService.createPet(new Pet("Georgie", "Cat", "God only knows", 3));
        } catch (BadDataException e) {
            throw new RuntimeException(e);
        }
        System.out.println(petService.getAllPets());
    }
}
