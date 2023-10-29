package com.aap.casitabackend.services

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.repositories.HouseholdsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class HouseholdsService(
    private val householdsRepository: HouseholdsRepository,
    private val usersService: UsersService
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

    fun updateHouseholdMembers(householdId: String, userIds: List<String>): ResponseEntity<Household> {
        val household = householdsRepository.findById(householdId).orElse(null)
        val users = usersService.findUsersByIds(userIds)
        if (household == null && users.isEmpty())
            return ResponseEntity(HttpStatus.NOT_FOUND)
        if (household != null && users.isEmpty())
            return ResponseEntity(household, HttpStatus.OK)
        if (household != null && users.isNotEmpty())
            household.householdMembers = users.toMutableList()
        return updateHousehold(householdId, household)
    }
}