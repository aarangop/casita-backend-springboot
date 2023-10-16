package com.aap.casitabackend.controllers

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.repositories.HouseholdsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/households")
class HouseholdsController(private val householdsRepository: HouseholdsRepository) {

    @GetMapping
    fun getHouseholds(): Iterable<Household> = householdsRepository.findAll()

    @GetMapping("/{id}")
    fun getHouseholdById(@PathVariable id: String): Optional<Household> = householdsRepository.findById(id)

    @PostMapping
    fun createHousehold(@RequestBody household: Household): Household {
        return householdsRepository.insert(household)
    }

    @PutMapping("/{id}")
    fun putHousehold(@PathVariable id: String, @RequestBody household: Household): ResponseEntity<Household> {

        val existingHousehold = householdsRepository.findById(id)

        if (existingHousehold.isEmpty) {
            return ResponseEntity(createHousehold(household), HttpStatus.CREATED)
        } else {
            return ResponseEntity(householdsRepository.save(household), HttpStatus.OK)
        }
    }
}

