package com.aap.casitabackend.controllers

import com.aap.casitabackend.entities.HouseholdMember
import com.aap.casitabackend.repositories.HouseholdMembersRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("household_members")
class HouseholdMembersController(private val householdMembersRepository: HouseholdMembersRepository) {
    @GetMapping
    fun getAllHouseholdMembers(): Iterable<HouseholdMember> {
        return householdMembersRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getHouseholdMember(@PathVariable id: String): Optional<HouseholdMember> {
        return householdMembersRepository.findById(id)
    }
}