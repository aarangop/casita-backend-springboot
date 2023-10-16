package com.aap.casitabackend.services

import com.aap.casitabackend.entities.User
import com.aap.casitabackend.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(
    private val usersRepository: UsersRepository
) {
    fun saveUser(user: User) = usersRepository.save(user)
}