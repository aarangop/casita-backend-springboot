package com.aap.casitabackend

import com.aap.casitabackend.entities.HouseholdMember
import com.aap.casitabackend.repositories.HouseholdsRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class HouseholdsService(
    private val mongoTemplate: MongoTemplate,
    private val householdsRepository: HouseholdsRepository
) {
    fun addHouseholdMember(householdId: String, householdMember: HouseholdMember) {
        val household = householdsRepository.findById(householdId).orElse(null)
        household.householdMembers.add(householdMember)
        householdsRepository.save(household)
//        val query = Query(Criteria.where("id").`is`(householdId))
//        val update = Update().addToSet("householdMembers", householdMember)
//        mongoTemplate.updateFirst(query, update, Household::class.java)
    }
}