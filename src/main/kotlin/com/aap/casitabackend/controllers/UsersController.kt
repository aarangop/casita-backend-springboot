package com.aap.casitabackend.controllers

import com.aap.casitabackend.entities.User
import com.aap.casitabackend.services.UsersService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = ["http://localhost:3000"])
class UsersController(private val usersService: UsersService) {
    /**
     * Get all users.
     *
     * @return A list of all users.
     */
    @GetMapping
    fun getUsers(): List<User> = usersService.getUsers()

    /**
     * Get a user by ID.
     *
     * @param id The ID of the user to get.
     * @return The user with the specified ID, or null if no such user exists.
     */
    @GetMapping("{id}")
    fun getUserById(@PathVariable id: String) = usersService.getUserById(id)

    /**
     * Get users by username or email.
     *
     * @param term The username or email to search for.
     * @return A list of users whose username or email matches the specified term.
     */
    @GetMapping("/email_or_username/")
    fun getUsersByUsernameOrEmail(@RequestParam term: String): List<User> =
        usersService.findUsersByUsernameOrEmail(term)
}