package com.aap.casitabackend.services

import com.aap.casitabackend.entities.User
import com.aap.casitabackend.repositories.UsersRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val template: MongoTemplate
) {
    fun getUsers(): List<User> = usersRepository.findAll().toList()
    fun getUserById(id: String): Optional<User> = usersRepository.findById(id)
    fun saveUsers(users: List<User>): List<User> = usersRepository.saveAll(users)
    fun findUsersByUsernameOrEmail(searchTerm: String): List<User> {
        val query = Query()
        val criteria = Criteria()
        criteria.orOperator(
            Criteria.where("username").regex("/${searchTerm}/"),
            Criteria.where("email").regex("/${searchTerm}/")
        )
        return template.find(query, User::class.java)
    }

    fun findUsersByIds(ids: List<String>): List<User> = usersRepository.findAllById(ids)
}