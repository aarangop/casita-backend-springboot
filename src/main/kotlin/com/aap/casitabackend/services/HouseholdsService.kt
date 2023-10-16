package com.aap.casitabackend.services

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.entities.HouseholdMember
import com.aap.casitabackend.repositories.HouseholdsRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class HouseholdsService(
    private val mongoTemplate: MongoTemplate,
    private val householdsRepository: HouseholdsRepository
) {
    fun saveHousehold(household: Household) = householdsRepository.save(household)

    fun addHouseholdMember(householdId: String, householdMember: HouseholdMember) {
        val household = householdsRepository.findById(householdId).orElse(null)
        household.householdMembers.add(householdMember)
        householdsRepository.save(household)
    }
}