package com.example.app_labs.controllers.rest;

import com.example.app_labs.entities.Household;
import com.example.app_labs.services.HouseholdService;
import com.example.app_labs.services.PetService;
import com.example.app_labs.services.exceptions.NonexistentRecordException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/households")
public class HouseholdsWebService {
    private PetService petService;
    private HouseholdService householdService;

    @GetMapping({"/", ""})
    public List<Household> getAllHouseholds() {
        return householdService.getAll();
    }

    @GetMapping({"/noPets"})
    public List<Household> getHouseholdsNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping({"/{eircode}"})
    public Household getHouseholdByEircode(@PathVariable("eircode") String eircode) throws NonexistentRecordException {
        return householdService.findHouseholdByEircode(eircode);
    }

    @DeleteMapping({"/{eircode}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouseholdByEircode(@PathVariable("eircode") String eircode) throws NonexistentRecordException {
        householdService.deleteByEircode(eircode);
    }

    @PostMapping({"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Household addHousehold(@RequestBody @Valid Household household) {
        householdService.createHousehold(household);
        return household;
    }

}
