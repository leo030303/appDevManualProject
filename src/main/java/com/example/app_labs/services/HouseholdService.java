package com.example.app_labs.services;

import com.example.app_labs.entities.Household;
import com.example.app_labs.services.exceptions.NonexistentRecordException;

import java.util.List;

public interface HouseholdService {
    Household findHouseholdByEircode(String eircode) throws NonexistentRecordException;
    Household findHouseholdByEircodeWithPets(String eircode) throws NonexistentRecordException;
    List<Household> findHouseholdsWithNoPets();
    List<Household> getAll();
    void deleteByEircode(String eircode) throws NonexistentRecordException;
    void createHousehold(Household household);
}
