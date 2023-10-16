package com.aap.casitabackend.controllers

import com.aap.casitabackend.entities.User
import com.aap.casitabackend.repositories.UsersRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(private val usersRepository: UsersRepository) {
    @GetMapping
    fun getAllUsers(): Iterable<User> = usersRepository.findAll()

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: String) = usersRepository.findById(id)
}