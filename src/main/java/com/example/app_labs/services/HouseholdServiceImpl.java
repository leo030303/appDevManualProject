package com.example.app_labs.services;

import com.example.app_labs.entities.Household;
import com.example.app_labs.daos.HouseholdRepository;
import com.example.app_labs.services.exceptions.NonexistentRecordException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {
    private HouseholdRepository householdRepository;

    @Override
    public Household findHouseholdByEircode(String eircode) throws NonexistentRecordException {
        return householdRepository.findById(eircode).orElseThrow(
                () -> new NonexistentRecordException("Household not found eircode: " + eircode)
        );
    }

    @Override
    public Household findHouseholdByEircodeWithPets(String eircode) throws NonexistentRecordException {
        return householdRepository.findHouseholdByEircode(eircode).orElseThrow(
                () -> new NonexistentRecordException("Household not found eircode: " + eircode)
        );
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> getAll() {
        return householdRepository.findAll();
    }

    @Override
    public void deleteByEircode(String eircode) throws NonexistentRecordException {
        householdRepository.deleteById(eircode);
    }

    @Override
    public void createHousehold(Household household) {
        householdRepository.save(household);
    }
}
