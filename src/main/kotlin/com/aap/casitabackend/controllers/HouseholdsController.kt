package com.aap.casitabackend.controllers

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.repositories.HouseholdsRepository
import com.aap.casitabackend.services.HouseholdsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/households")
@CrossOrigin(origins = ["http://localhost:3000"])
class HouseholdsController(
    private val householdsRepository: HouseholdsRepository,
    private val householdsService: HouseholdsService
) {

    @GetMapping
    fun getHouseholds(): List<Household> = householdsService.getAllHouseholds()

    @GetMapping("/{id}")
    fun getHouseholdById(@PathVariable id: String): Optional<Household> = householdsService.getHouseholdById(id)

    @PostMapping
    fun createHousehold(@RequestBody household: Household): Household = householdsService.saveHousehold(household)

    @PutMapping("/{id}")
    fun putHousehold(@PathVariable id: String, @RequestBody household: Household): ResponseEntity<Household> =
        householdsService.updateHousehold(id, household)
}

