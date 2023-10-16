package com.aap.casitabackend.services

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.entities.HouseholdMember
import com.aap.casitabackend.repositories.HouseholdsRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class HouseholdsService(
    private val mongoTemplate: MongoTemplate,
    private val householdsRepository: HouseholdsRepository
) {
    fun saveHousehold(household: Household) = householdsRepository.save(household)

    fun getAllHouseholds(): List<Household> = householdsRepository.findAll()

    fun getHouseholdById(householdId: String): Optional<Household> = householdsRepository.findById(householdId)

    fun updateHousehold(householdId: String, household: Household): ResponseEntity<Household> {
        val existingHousehold = householdsRepository.findById(householdId)
        return ResponseEntity(
            saveHousehold(household),
            if (existingHousehold.isEmpty) HttpStatus.CREATED else HttpStatus.OK
        )
    }

    fun addHouseholdMember(householdId: String, householdMember: HouseholdMember) {
        val household = householdsRepository.findById(householdId).orElse(null)
        household.householdMembers.add(householdMember)
        householdsRepository.save(household)
    }
}