package com.example.app_labs;

import com.example.app_labs.services.HouseholdService;
import com.example.app_labs.services.PetService;
import com.example.app_labs.services.PetServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AppLabsApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(AppLabsApplication.class, args);

        PetService petService = context.getBean(PetServiceImpl.class);
        System.out.println(petService.getAverageAge());
        System.out.println(petService.getOldestAge());
        HouseholdService householdService = context.getBean(HouseholdService.class);
        System.out.println(householdService.findHouseholdsWithNoPets());
    }
}
