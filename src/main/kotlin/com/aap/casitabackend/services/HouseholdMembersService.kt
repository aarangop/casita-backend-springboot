package com.aap.casitabackend.services

import com.aap.casitabackend.entities.HouseholdMember
import com.aap.casitabackend.repositories.HouseholdMembersRepository
import org.springframework.stereotype.Service

@Service
class HouseholdMembersService(
    private val householdMembersRepository: HouseholdMembersRepository
) {
    fun saveHouseholdMember(householdMember: HouseholdMember) = householdMembersRepository.save(householdMember)
}