package com.aap.casitabackend.controllers

import com.aap.casitabackend.entities.User
import com.aap.casitabackend.services.UsersService
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@AutoConfigureJsonTesters
@WebMvcTest(UsersController::class)
class UsersControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val jsonUsers: JacksonTester<List<User>>
) {

    @MockkBean
    private lateinit var usersService: UsersService

    private val user1 = User("1", "User", "One", "user1", "user1", "password", "user@mail.com")
    private val user2 = User("2", "Another", "User", "user2", "user2", "password", "user2@mail.com")

    @BeforeEach
    fun setup() {
        JacksonTester.initFields(this, ObjectMapper())
    }

    @Test
    fun `given existing users return list of users when a get requests is performed`() {
        every { usersService.getUsers() } returns listOf(user1, user2)

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()", `is`(2)))
    }

    @Test
    fun `given a user exists, the user data will be returned when querying by id`() {
        every { usersService.getUserById("1") } returns Optional.of(user1)

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", `is`(1)))
            .andExpect(jsonPath("$.name").value("User"))
            .andExpect(jsonPath("$.lastName").value("One"))
    }

    @Test
    fun `test that the response of a user query by id does not contain password and email`() {

        every { usersService.getUserById("1") } returns Optional.of(user1)

        mockMvc.perform(get("/api/users/1"))
            .andExpect(jsonPath("$.password").doesNotExist())
            .andExpect(jsonPath("$.email").doesNotExist())
    }
}
