package com.aap.casitabackend.controllers

import com.aap.casitabackend.repositories.HouseholdsRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/households")
class HouseholdsWebController(private val householdsRepository: HouseholdsRepository) {
    @GetMapping
    fun getHouseholds(model: Model): String {
        model.addAttribute("households", householdsRepository.findAll())
        return "households"
    }
}
