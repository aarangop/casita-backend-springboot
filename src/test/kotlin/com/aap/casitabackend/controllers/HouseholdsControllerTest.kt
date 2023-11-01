package com.aap.casitabackend.controllers

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.entities.User
import com.aap.casitabackend.services.HouseholdsService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(HouseholdsController::class)
class HouseholdsControllerTest(
    @Autowired val mockMvc: MockMvc
) {
    @MockkBean
    private lateinit var householdsService: HouseholdsService

    private val household1 = Household(
        id = "1",
        street = "Test Street",
        houseNumber = "42",
        zipCode = "1000",
        city = "Test City 1",
        country = "Test Country",
        householdMembers = emptyList<User>().toMutableList()
    )

    private val household2 = Household(
        id = "2",
        street = "Test Street 2",
        houseNumber = "43",
        zipCode = "1001",
        city = "Test City 2",
        country = "Test Country 2",
        householdMembers = emptyList<User>().toMutableList()
    )

    @Test
    fun `given a list of persisted households, when get endpoint is called, then return the list of households`() {
        every { householdsService.getAllHouseholds() } returns listOf(household1, household2)

        mockMvc.perform(get("/api/households"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()", `is`(2)))
    }
}