package com.example.app_labs.daos;

import com.example.app_labs.entities.Household;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HouseholdRepository extends JpaRepository<Household, String> {
    @EntityGraph(attributePaths = {"pets"})
    Optional<Household> findHouseholdByEircode(String eircode);

    @Query("SELECT new com.example.app_labs.entities.Household(h.eircode, h.numberOfOccupants, h.maxNumberOfOccupants, h.ownerOccupied) FROM Household h LEFT JOIN Pet p ON h.eircode = p.household.eircode WHERE p.id IS NULL")
    List<Household> findHouseholdsWithNoPets();

}
